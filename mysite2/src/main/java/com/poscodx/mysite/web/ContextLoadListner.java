package com.poscodx.mysite.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

public class ContextLoadListner implements ServletContextListener {


    public ContextLoadListner() {
       
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext sc = sce.getServletContext();
    	String contextConfigLocation = sc.getInitParameter("contextConfigLocation");
    	
    	
    	System.out.println("Application[Mysite2] starts...."+contextConfigLocation);
    }
    
    
    public void contextDestroyed(ServletContextEvent sce)  { 
       
    }



	
}