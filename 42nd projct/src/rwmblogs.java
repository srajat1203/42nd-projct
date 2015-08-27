

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Mblog;
import customTools.DBUtil;

import java.util.ArrayList;
/**
 * Servlet implementation class helloworld
 */



@WebServlet("/rwmblogs")
public class rwmblogs extends HttpServlet {
	

	String err = "<div class=\"alert alert-danger\"> <strong>Error!</strong> You cannot have more than 50 chars </div>";
	String tmp = "";
	
	
	  public void init() throws ServletException
	  {
	      // Do required initialization
		 
	  }

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
		  
		  HttpSession session = request.getSession();
		
		 String countd = (String) session.getAttribute("countd");
		 System.out.println(countd); 
		 
		 int count = 0;
		 int i = 0;
		 if(countd == null)
		 {
			 System.out.println("countd");
			 session.setAttribute("count",0);
			 session.setAttribute("countd", "done");
			 count = (Integer) session.getAttribute("count");
			 i = count;
		 }
		 else
		 {
			 System.out.println("co");
			 count = (Integer) session.getAttribute("count");
			 i = count;
		 }
		 
		 
		 
		 
		 //session.setAttribute("count", )
		  
		  
		  long id = count + 1;
		  String post = request.getParameter("post");
		  
		  Mblog m = new Mblog();
		  m.setId(id);
		  m.setPost(post);
		  
		  
		  EntityManager em = DBUtil.getEmFactory().createEntityManager();
			EntityTransaction trans = em.getTransaction();
			
			   
		
	if(post != null)		
	{	  
		if(!post.isEmpty())	 
		{
			if(post.length() >50)
			{
				//String message = "You cannot go over 50 chars";
				request.setAttribute("err", err);
			}
			else
			{
				
				trans.begin();
				try{
					  em.persist(m);
					  trans.commit();
				  }catch (Exception e) {
					  trans.rollback();
				  } finally {
					  //em.close();
					  session.setAttribute("count", (count+1));
					  i++;
				  }
			} 
		} 
	}	
		  
	
	tmp = "";
	
	
	
	try {
		while(i>0)
		{
			model.Mblog mblog = em.find(model.Mblog.class, (long)i);
			System.out.println(mblog.getPost());
			tmp = tmp +  mblog.getPost() + "<br><br>";
			i--;
		}
	} catch (Exception e){
		System.out.println(e);
	} finally {
		em.close();
		System.out.println("cerrado!");
		
	}
	 
		  
	      response.setContentType("text/html");
	      
	      request.setAttribute("tmp", tmp);
	      
	      
	      getServletContext()
	      	.getRequestDispatcher("/rwmblogsdisp.jsp")
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
