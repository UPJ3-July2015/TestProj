package me.hbtest.testproj;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.common.io.ByteStreams;

/**
 * Hello all!
 * 1. put your image to resource
 * 2. change UserId and ImageName
 */
public class App 
{	
	private static final Long UserId = 168L;	
	private static final String ImageName = "professor.jpg";
	
	
    public static void main( String[] args ) throws Exception  
    {    
    	byte[] image = ByteStreams.toByteArray( new App().getClass().getResourceAsStream("/"+ImageName) ); 
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("app");
    	try {
    		EntityManager em = emf.createEntityManager();
    		try {
    			em.getTransaction().begin();
    			User u2 = em.find(User.class, UserId);
    			u2.setAvatar(image);
    			em.getTransaction().commit();
    		}finally {
    		 em.close();	
    		}    		
    	} finally {
    	  emf.close();
    	}    	
    }
}
