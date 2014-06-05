package org.ithos.assignment.persistence.test;

import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.ithos.assignment.persistence.JPAAnimalDelegate;
import org.ithos.assignment.persistence.model.Location;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.collect.Lists;

public class BaseTestCase {
	protected static ApplicationContext rootCtx;
	protected static List<String> locationNames;
	@BeforeClass
	public static void preProcess(){
		rootCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
		locationNames = createSomeLocations();
	}
	
	private static List<String> createSomeLocations() {
		List<String> locationNames = Lists.newArrayList();
		Integer randomInteger = RandomUtils.nextInt();
		Location india = new Location("India-"+randomInteger);
		Location germany = new Location("Germany-"+randomInteger);
		Location uk = new Location("UK-"+randomInteger);
		JPAAnimalDelegate delegate = (JPAAnimalDelegate)rootCtx.getBean(JPAAnimalDelegate.class);
		delegate.insertModel(india);
		delegate.insertModel(germany);
		delegate.insertModel(uk);
		locationNames.add(india.getPlace());
		locationNames.add(germany.getPlace());
		locationNames.add(uk.getPlace());
		return locationNames;
	}

	@AfterClass
	public static void postProcess(){
		((ClassPathXmlApplicationContext)rootCtx).close();
	}	
}
