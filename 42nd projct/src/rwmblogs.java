

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
import customTools.DBUtil;

import java.util.ArrayList;
/**
 * Servlet implementation class helloworld
 */



@WebServlet("/rwmblogs")
public class rwmblogs extends HttpServlet {
	

	String err = "<div class=\"alert alert-danger\"> <strong>Error!</strong> You cannot have more than 50 chars </div>";
	String tmp = "";
	
	
	String postform = "<div class=\"container\"> <h2>Enter post</h2> <form role=\"form\" action=\"rwmblogs\"> <div class=\"form-group\"> <label for=\"post\">Post:</label> <input type=\"text\" class=\"form-control\" id=\"post\" name =\"post\" placeholder=\"Enter post\"> </div> <button type=\"submit\" class=\"btn btn-default\">Submit</button> </form></div>";
	
	
	boolean read;
	
	  public void init() throws ServletException
	  {
	      // Do required initialization
		 
	  }

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
		  read = true;
		  
		  HttpSession session = request.getSession();
	
		  String found = (String) session.getAttribute("found");
		  
		  
		  EntityManager em = DBUtil.getEmFactory().createEntityManager();
			EntityTransaction trans = em.getTransaction();
			
	if(found != null)	
	{
		if(found.equals("no"))
		{
			read = false;
		}
	
		if(found.equals("yes"))	
		
		{	
			request.setAttribute("postform", postform);
			
		  	
			
		  String post = request.getParameter("post");
		  
		  
		  Mblog m = new Mblog();
		  m.setPost(post);
			
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
							//System.out.println("a");
							  em.persist(m);
							  trans.commit();
						  }catch (Exception e) {
							  System.out.println("catch");
							  trans.rollback();
						  } finally {
							 // em.close();
							 // session.setAttribute("count", (count+1));
							 
						  }
					}
				}
			}	
		}
	}	
		
	
	String searchq = request.getParameter("search");
	System.out.println(searchq);
	
	if(searchq == null)
	{
			String qString = "SELECT m FROM Mblog m";
			
			TypedQuery<Mblog> q =  em.createQuery(qString, Mblog.class);
			
			List<Mblog> mblogs = q.getResultList();
			
			Collections.reverse(mblogs);
			try {
				tmp = " ";
				for (Mblog cur : mblogs) {
					
					tmp = tmp + cur.getPost() + "<br><br>"; 
				}
			} catch (Exception e){
				e.printStackTrace();
			} finally {
				em.close();
			}
	}	

			//Searching word now
			
			
			if(searchq != null)
			{
				if(!searchq.isEmpty())
				{
					//String search = "SELECT m FROM Mblog m WHERE m.POST LIKE ?";
					String search = "SELECT m FROM Mblog m where m.post like '%" + searchq +"%'";
					
					TypedQuery<Mblog> q2 =  em.createQuery(search, Mblog.class);
					//q2.setParameter(1, "%" + searchq + "%"); 
					List<Mblog> mblogs2 = q2.getResultList();
					
					try {
						tmp = " ";
						for (Mblog cur : mblogs2) {
							
							tmp = tmp + cur.getPost() + "<br><br>"; 
						}
					} catch (Exception e){
						e.printStackTrace();
					} finally {
						em.close();
					}
				}
				
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
