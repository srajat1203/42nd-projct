

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Mblog;
import model.Usr;
import customTools.DBUtil;

import java.util.ArrayList;
/**
 * Servlet implementation class helloworld
 */



@WebServlet("/Login")
public class Login extends HttpServlet {
	

	
	String found;
	String loginerr = "<div class=\"alert alert-danger\"> <strong>Error!</strong> User not found </div>";
	
	  public void init() throws ServletException
	  {
	      // Do required initialization
		 
	  }

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
		  
		  HttpSession session = request.getSession();
		  
		  found = "no";
		
		  String email = request.getParameter("email");
		  System.out.println(email);
		
		  
		  EntityManager em = DBUtil.getEmFactory().createEntityManager();
			EntityTransaction trans = em.getTransaction();
			
	
	String qString = "SELECT u FROM Usr u";
	
	TypedQuery<Usr> q =  em.createQuery(qString, Usr.class);
	
	List<Usr> users = q.getResultList();
	
	
	
	
	try {
		
		for (Usr cur : users) {
			
			if(cur.getEmail().equals(email))
			{
				found = "yes";
			
				System.out.println(cur.getName());
				System.out.println(cur.getMoto());
			}
			
		}
	} catch (Exception e){
		e.printStackTrace();
	} finally {
		em.close();
	}

	System.out.println(found);
	
	session.setAttribute("found", found);
	
	
	
	
	
	      response.setContentType("text/html");
	      
	      request.setAttribute("loginerr", loginerr);
	      
	      String next1 = "/rwmblogs";
	      String next2 = "/Logindisp.jsp";
	      String choice = "";
	      
	      if(found.equals("yes"))
	      {
	    	  choice = next1;
	      }
	      else
	      {
	    	  choice = next2;
	      }
	      
	      getServletContext()
	      	.getRequestDispatcher(choice)
	      	.forward(request, response);
	 
	   }
	  
	  public void doPost(HttpServletRequest request,
              HttpServletResponse response)
      throws ServletException, IOException
      {
		  doGet(request, response);
      }

	   public void destroy() 
	   { 
		   
	   } 

}
