package com.haojii.prototype.tas.spring;

import java.util.Comparator;

import org.springframework.stereotype.Service;

/*
 * For simplicity's sake, this class has itself been annotated with Spring's @Service Stereotype annotation,
 *  a type of @Component and thus will automatically be detected by the Spring container as part of the container's component scanning process, and injected into the controller.
 */
@Service
public class CaseInsensitiveComparator implements Comparator<String>{

	@Override
	public int compare(String s1, String s2) {
        assert s1 != null && s2 != null;
        return String.CASE_INSENSITIVE_ORDER.compare(s1, s2);

	}

}
