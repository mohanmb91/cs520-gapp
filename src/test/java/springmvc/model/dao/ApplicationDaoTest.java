package springmvc.model.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import springmvc.model.Application;

@Test(groups = "ApplicationDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ApplicationDaoTest extends AbstractTransactionalTestNGSpringContextTests{
	 @Autowired
	 ApplicationDao applicationDao;
	/* 
	 @Test
	 public void getApplicationForFall2016InAccounting(){
		 List<Application> applications =  applicationDao.getApplications();
		 for(Application eachApplication: applications){
			 if(eachApplication.getProgram().getDepartment().getName().equals("Accounting")){
				 assert eachApplication.getTerm().equals("Fall 2016");
			 }
		 }
	 }
	 @Test
	 public void checkStudent1HasOneApplication(){
		 int count = 0;
		 List<Application> applications =  applicationDao.getApplications();
		 for(Application eachApplication: applications){
			 if(eachApplication.getStudent().getEmailId().equals("student1@localhost.localdomain")){
				 count = count + 1;
			 }
		 }
		 //this means student1 has one application.
		 assert count == 1;
		 }*/
	 }

