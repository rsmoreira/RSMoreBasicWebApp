package com.rsmore.ejb;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rsmore.ejb.entities.Project;
import com.rsmore.ejb.service.remote.ProjectBeanRemote;
import com.rsmore.ejb.service.remote.RemoteCalculator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/beans.xml")
public class EjbRemoteAccessTest {

	private static Context ctx;
//	private static ApplicationContext springCtx;

	@BeforeClass
	public static void setup() throws NamingException {
		Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.openejb.client.RemoteInitialContextFactory");
		prop.put(Context.PROVIDER_URL, "ejbd://localhost:4201");
		ctx = new InitialContext(prop);
		// springCtx = new ClassPathXmlApplicationContext(ctx);

	}

	@AfterClass
	public static void tearDown() throws NamingException {
		ctx.close();
	}

	@Test
	public void remoteCalculatorTest() throws NamingException {
		RemoteCalculator remoteCalculator = (RemoteCalculator) ctx
				.lookup("global/RSMoreEJB31App-0.0.1-SNAPSHOT/CalculatorBean!com.rsmore.ejb.service.remote.RemoteCalculator");
		int added = remoteCalculator.add(1, 1);
		Assert.assertEquals(2, added);
		System.out.println("Frank is alive!");
	}
	
	@Test
	public void persistProjectTest() throws NamingException {
		ProjectBeanRemote projectBeanRemote = (ProjectBeanRemote) ctx
				.lookup("global/RSMoreEJB31App-0.0.1-SNAPSHOT/ProjectBean!com.rsmore.ejb.service.remote.ProjectBeanRemote");
		
		Project project = new Project();
		project.setPname("RemoteAccessTest");
		project.setPlocation("from another app");
		project.setDeptNo(3);
		
		projectBeanRemote.saveProject(project);
		
		List<Project> projects = projectBeanRemote.retrieveAllProjects();
		
		for (Project p : projects) {
			System.out.println(p.toString());
		}
		
	}

}
