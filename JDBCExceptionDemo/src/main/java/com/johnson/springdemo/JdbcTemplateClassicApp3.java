package com.johnson.springdemo;

import com.johnson.springdemo.dao.OrganizationDao;
import com.johnson.springdemo.domain.Organization;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;

import java.util.List;

public class JdbcTemplateClassicApp3 {

	public static void main(String[] args) {
		
		// creating the application context
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-cp.xml");
		
		// Create the bean
		OrganizationDao dao = (OrganizationDao) ctx.getBean("orgDao");
		List<Organization> orgs = null;
		try {
			orgs = dao.getAllOrganizations();
		} catch(BadSqlGrammarException bge){
			System.out.println("Sub Exception message" + bge.getMessage());
			System.out.println("Sub Exception class" + bge.getClass());
		} catch (DataAccessException dae){
			System.out.println("Exception message" + dae.getMessage());
			System.out.println("Exception class" + dae.getClass());
		}

		for (Organization org : orgs) {
			System.out.println(org);
		}
		
		// close the application context
		((ClassPathXmlApplicationContext) ctx).close();

	}

}
