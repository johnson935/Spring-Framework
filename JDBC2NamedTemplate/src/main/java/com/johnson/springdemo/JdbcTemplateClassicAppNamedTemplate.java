package com.johnson.springdemo;

import com.johnson.springdemo.dao.OrganizationDao;
import com.johnson.springdemo.domain.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcTemplateClassicAppNamedTemplate {

	@Autowired
	private OrganizationDao dao;

	@Autowired
	private DaoUtils daoUtils;

	public void actionMethod() {
		daoUtils.createSeedData(dao);

		//List Orgs
		List<Organization> orgs = dao.getAllOrganizations();
		daoUtils.printOrganizations(orgs, daoUtils.readOperation);

		//Create a new org
		Organization org = new Organization("General Electric", 1978, 98989, 5776, "Imagination at work");
		boolean isCreated = dao.create(org);
		daoUtils.printSuccessFailure(daoUtils.createOperation, isCreated);
		daoUtils.printOrganizations(dao.getAllOrganizations(), daoUtils.readOperation);

		//get one org
		Organization org2 = dao.getOrganization(2);
		daoUtils.printOrganization(org2, "getOrganization");

		//Update
		Organization org3 = dao.getOrganization(2);
		org3.setSlogan("Awesome driving machines");
		boolean isUpdated = dao.update(org3);
		daoUtils.printSuccessFailure(daoUtils.updateOperation, isUpdated);
		daoUtils.printOrganization(dao.getOrganization(2), daoUtils.updateOperation);

		//Delete
		boolean isDeleted = dao.delete(dao.getOrganization(3));
		daoUtils.printSuccessFailure(daoUtils.deleteOperation, isDeleted);
		daoUtils.printOrganizations(dao.getAllOrganizations(), daoUtils.deleteOperation);

		//Dao clean up
		dao.cleanup();
		daoUtils.printOrganizationCount(dao.getAllOrganizations(), daoUtils.cleanupOperation);

	}
	public static void main(String[] args) {
		
		// creating the application context
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-cp.xml");
		JdbcTemplateClassicAppNamedTemplate mainApp = ctx.getBean(JdbcTemplateClassicAppNamedTemplate.class);
		mainApp.actionMethod();
		// Create the bean
		//OrganizationDao dao = (OrganizationDao) ctx.getBean("orgDao");

		// close the application context
		((ClassPathXmlApplicationContext) ctx).close();
	}
}
