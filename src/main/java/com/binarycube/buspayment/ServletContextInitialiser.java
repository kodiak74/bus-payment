package com.binarycube.buspayment;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.binarycube.buspayment.util.CloudStorageHelper;
 

public class ServletContextInitialiser implements ServletContextListener {
	
   public void contextInitialized(ServletContextEvent sce)    {
	   System.out.println("Starting up bus payment system...");
       ServletContext sc = sce.getServletContext();
       CloudStorageHelper storageHelper = new CloudStorageHelper();
       sc.setAttribute("storageHelper", storageHelper);
      
        
   }

   public void contextDestroyed(ServletContextEvent sce)   {
	   System.out.println("Shutting down bus payment system...");
       ServletContext sc = sce.getServletContext();
       
       //TODO: Cleanup here
       

   }
}