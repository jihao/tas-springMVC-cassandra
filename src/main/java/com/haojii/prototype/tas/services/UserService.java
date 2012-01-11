package com.haojii.prototype.tas.services;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.haojii.prototype.tas.dao.UserDAO;
import com.haojii.prototype.tas.dao.UserDAOCassandraImpl;
import com.haojii.prototype.tas.model.User;

@Service
public class UserService implements IUserService {

	private UserDAO userDAO = new UserDAOCassandraImpl();
	private static Logger logger = Logger.getLogger(UserService.class);
			
	/* (non-Javadoc)
	 * @see com.haojii.prototype.tas.services.IUserService#insertUser(com.haojii.prototype.tas.model.User)
	 */
	@Override
	public boolean insertUser(User user) {
		return userDAO.insertUser(user);
	}

	/* (non-Javadoc)
	 * @see com.haojii.prototype.tas.services.IUserService#deleteUser(java.lang.String)
	 */
	@Override
	public boolean deleteUser(String username) {
		return userDAO.deleteUser(username);
	}

	/* (non-Javadoc)
	 * @see com.haojii.prototype.tas.services.IUserService#findUser(java.lang.String)
	 */
	@Override
	public User findUser(String username) {
		return userDAO.findUser(username);
	}

	/* (non-Javadoc)
	 * @see com.haojii.prototype.tas.services.IUserService#updateUser(com.haojii.prototype.tas.model.User)
	 */
	@Override
	public boolean updateUser(User user) {
		return userDAO.updateUser(user);
	}

	/* (non-Javadoc)
	 * @see com.haojii.prototype.tas.services.IUserService#listUsers()
	 */
	@Override
	public Collection<User> listUsers() {
		return userDAO.listUsers();
	}
	
	/* (non-Javadoc)
	 * @see com.haojii.prototype.tas.services.IUserService#countUsers()
	 */
	@Override
	public int countUsers(){
		return userDAO.countUsers();
	}
	
	/* (non-Javadoc)
	 * @see com.haojii.prototype.tas.services.IUserService#verifyUsernamePassword(com.haojii.prototype.tas.model.User)
	 */
	@Override
	public boolean verifyUsernamePassword(User user) {
		User persistUser = findUser(user.getUsername());
		if (persistUser == null) {
			return false;
		}
		String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		logger.debug("user input : MD5(password)=" + md5Password);
		logger.debug("database   : MD5(password)=" + persistUser.getPassword());
		
		return persistUser.getPassword().equals(md5Password);
	}
}
