package com.LibraryManagement.Helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class factoryProvider {

	public static SessionFactory factory;
	
	public static SessionFactory getfactory() {
		if(factory == null) {
			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return factory;
	}
	public void closefactory() {
		if(factory.isOpen()) {
			factory.close();
		}
	}
	}
