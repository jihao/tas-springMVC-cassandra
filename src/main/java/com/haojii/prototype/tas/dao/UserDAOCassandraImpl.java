package com.haojii.prototype.tas.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import me.prettyprint.cassandra.dao.SimpleCassandraDao;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.template.ColumnFamilyTemplate;
import me.prettyprint.cassandra.service.template.ThriftColumnFamilyTemplate;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.beans.OrderedRows;
import me.prettyprint.hector.api.beans.Row;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.CountQuery;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.RangeSlicesQuery;
import me.prettyprint.hector.api.query.SliceQuery;

import org.apache.log4j.Logger;
import org.springframework.util.DigestUtils;

import com.haojii.prototype.tas.model.User;

/**
 * User DAO Cassandra Implementation
 * <br><br>
 * in order to learn Hector, in this DAO, I use 3 ways to access data<br>
 *  1. use SimpleCassandraDao<br>
 *  2. use HFactory.createMutator etc.<br>
 *  3. use ThriftColumnFamilyTemplate<br>
 *  <p>
 *  Note: the cassandra APIs will throw runtime exception HectorException
 *  </p>
 * @author hao
 *
 */
public class UserDAOCassandraImpl implements UserDAO {
	private static Logger logger = Logger.getLogger(UserDAOCassandraImpl.class);
	
	private static final String CF_NAME = "Users";
	private static final String KS_NAME = "tas_springMVC_cassandra";
	private static final String HOST_PORT = "localhost:9160";
	
	private static final String CN_PASSWORD = "password";
	private static final String CN_CREATED_AT = "created_at";
	
	public UserDAOCassandraImpl() {
		cassandraDao = new SimpleCassandraDao();
		Cluster cluster = HFactory.getOrCreateCluster("MyCluster", HOST_PORT);
		keyspace = HFactory.createKeyspace(KS_NAME, cluster);
		
		cassandraDao.setKeyspace(keyspace);
		cassandraDao.setColumnFamilyName(CF_NAME);
	}

	private SimpleCassandraDao cassandraDao;
	private Keyspace keyspace;
	
	@Override
	public boolean insertUser(User user) {
		String key = user.getUsername();

		String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateFormatted = formatter.format(user.getCreated_at());
		
		Mutator<String> mutator = HFactory.createMutator(keyspace, StringSerializer.get());
		
		mutator.addInsertion(key, CF_NAME, HFactory.createColumn(CN_PASSWORD, md5Password, StringSerializer.get(), StringSerializer.get()));
		mutator.addInsertion(key, CF_NAME, HFactory.createColumn(CN_CREATED_AT, dateFormatted, StringSerializer.get(), StringSerializer.get()));
		mutator.execute();
		
		//cassandraDao.insert(key, CN_PASSWORD, md5Password);
		//cassandraDao.insert(key, CN_CREATED_AT, dateFormatted);
		return true;
	}

	@Override
	public boolean deleteUser(String username) {
		//seems this is not an easy API to delete one single row
		//cassandraDao.delete(columnName, keys)
		//
		//try another way
		ColumnFamilyTemplate<String, String> template =
                new ThriftColumnFamilyTemplate<String, String>(keyspace, CF_NAME, StringSerializer.get(), StringSerializer.get());
		template.deleteRow(username);
		return true;
	}

	@Override
	public User findUser(String username) {
		//check if row key exist, then query
		CountQuery<String, String> countQuery = HFactory.createCountQuery(keyspace, StringSerializer.get(), StringSerializer.get());
		countQuery.setColumnFamily(CF_NAME);
		countQuery.setKey(username);
		countQuery.setRange(null, null, 100);
		QueryResult<Integer> countResult = countQuery.execute();
		int count = countResult.get();
		if(count==0) 
		{
			return null;
		} 
		else
		{
			User user = new User();
			user.setUsername(username);
			
			SliceQuery<String, String, String>  sliceQuery = HFactory.createSliceQuery(keyspace, StringSerializer.get(), StringSerializer.get(), StringSerializer.get());
			sliceQuery.setColumnFamily(CF_NAME);
			sliceQuery.setRange(null, null, false, 1000);
			sliceQuery.setKey(username);
			sliceQuery.setColumnNames(CN_PASSWORD,CN_CREATED_AT);
			
			QueryResult<ColumnSlice<String, String>> result = sliceQuery.execute();
			ColumnSlice<String, String> columnSlice= result.get();
			
			logger.debug("columnSlice isNull?="+(columnSlice==null));
			logger.debug("columnSlice.getColumnByName(CN_PASSWORD) isNull?="+(columnSlice.getColumnByName(CN_PASSWORD)==null)); 
			HColumn<String, String> columnPassword = columnSlice.getColumnByName(CN_PASSWORD);
			String md5Password = (columnPassword != null ? columnPassword.getValue() : null);
			
			HColumn<String, String> columnDate = columnSlice.getColumnByName(CN_CREATED_AT);
			String dateStr = (columnDate != null ? columnDate.getValue() : null);
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date date = formatter.parse(dateStr);
				user.setCreated_at(date);
			} catch (ParseException e) {
				logger.debug(e.getMessage());
			}
			user.setPassword(md5Password);
			
			/*String md5password = cassandraDao.get(username, CN_PASSWORD);
			user.setPassword(md5password);
			
			String dateStr = cassandraDao.get(username, CN_CREATED_AT);
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date date = formatter.parse(dateStr);
				user.setCreated_at(date);
			} catch (ParseException e) {
				logger.debug(e.getMessage());
			}*/
			
			return user;
		}
	}

	@Override
	public boolean updateUser(User user) {
		String key = user.getUsername();

		String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateFormatted = formatter.format(user.getCreated_at());
		
		Mutator<String> mutator = HFactory.createMutator(keyspace, StringSerializer.get());
		
		mutator.addInsertion(key, CF_NAME, HFactory.createColumn(CN_PASSWORD, md5Password, StringSerializer.get(), StringSerializer.get()));
		mutator.addInsertion(key, CF_NAME, HFactory.createColumn(CN_CREATED_AT, dateFormatted, StringSerializer.get(), StringSerializer.get()));
		mutator.execute();
		
		//cassandraDao.insert(key, CN_PASSWORD, md5Password);
		//cassandraDao.insert(key, CN_CREATED_AT, dateFormatted);
		return true;
	}

	@Override
	public Collection<User> listUsers() {
		List<User> userList = new ArrayList<User>();
		RangeSlicesQuery<String, String, String> rangeSlicesQuery = HFactory.createRangeSlicesQuery(keyspace, StringSerializer.get(), StringSerializer.get(), StringSerializer.get());
		rangeSlicesQuery.setColumnFamily(CF_NAME);
		rangeSlicesQuery.setColumnNames(CN_PASSWORD,CN_CREATED_AT);
		rangeSlicesQuery.setRowCount(1000);
		//rangeSlicesQuery.setRange(start, finish, reversed, count)
		QueryResult<OrderedRows<String, String, String>> result = rangeSlicesQuery.execute();
		OrderedRows<String, String, String> rows = result.get();
		Iterator<Row<String, String, String>> itr = rows.iterator();
		while(itr.hasNext()) {
			Row<String, String, String> row = itr.next();
			ColumnSlice<String, String> columnSlice = row.getColumnSlice();
			
			User user = new User();
			
			String username = row.getKey();
			user.setUsername(username);
			
			String md5Password = columnSlice.getColumnByName(CN_PASSWORD).getValue();
			user.setPassword(md5Password);
			
			String dateStr = columnSlice.getColumnByName(CN_CREATED_AT).getValue();
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date date = formatter.parse(dateStr);
				user.setCreated_at(date);
			} catch (ParseException e) {
				logger.debug(e.getMessage());
			}
			
			userList.add(user);
		}
		
		return userList;
	}

	@Override
	public int countUsers() {
		CountQuery<String, String> countQuery = HFactory.createCountQuery(keyspace, StringSerializer.get(), StringSerializer.get());
		countQuery.setColumnFamily(CF_NAME);
		QueryResult<Integer> result = countQuery.execute();
		return result.get();
	}

}
