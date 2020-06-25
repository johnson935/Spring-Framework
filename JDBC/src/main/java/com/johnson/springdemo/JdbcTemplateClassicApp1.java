package com.johnson.springdemo;

import com.johnson.springdemo.dao.OrganizationDao;
import com.johnson.springdemo.domain.Organization;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class JdbcTemplateClassicApp1 {

	public static void main(String[] args) {
		
		// creating the application context
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-cp.xml");
		
		// Create the bean
		OrganizationDao dao = (OrganizationDao) ctx.getBean("orgDao");
		List<Organization> orgs = dao.getAllOrganizations();
		for (Organization org : orgs) {
			System.out.println(org);
		}
		
		// close the application context
		((ClassPathXmlApplicationContext) ctx).close();

	}

}
