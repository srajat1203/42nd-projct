

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



@WebServlet("/Userprofile")
public class Userprofile extends HttpServlet {
	
	String tmp = "";
	String users = "";
	
	
	
	  public void init() throws ServletException
	  {
	      // Do required initialization
		 
	  }

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
		  
		  HttpSession session = request.getSession();
	
		 
		  
		  EntityManager em = DBUtil.getEmFactory().createEntityManager();
		  EntityTransaction trans = em.getTransaction();
			
	
		 
		String user = request.getParameter("user");
		
		if(user == null)
		{
			user = (String) session.getAttribute("user");
		}
		else
		{
			session.setAttribute("user", user);
		}
		
		String searchp = request.getParameter("searchp");
		
		
		String qname = "SELECT u FROM Usr u where u.email = '"+ user+"'";
		TypedQuery<Usr> u =  em.createQuery(qname, Usr.class);
		Usr us = u.getSingleResult();
		String uname = us.getName();
		String umoto = us.getMoto();
		String ujoindate = us.getJoindate();
		
		
	if(searchp == null)
	{
			String qString = "SELECT m FROM Mblog m where m.uemail = '" + user + "' order by m.id desc";
			
			TypedQuery<Mblog> q =  em.createQuery(qString, Mblog.class);
			
			List<Mblog> mblogs = q.getResultList();
			
			//Collections.reverse(mblogs);
	
			
			
			try {
				tmp = " ";
				users = " ";
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
			

			if(searchp != null)
			{
				if(!searchp.isEmpty())
				{
					//String search = "SELECT m FROM Mblog m WHERE m.POST LIKE ?";
					String search = "SELECT m FROM Mblog m where m.uemail = '" + user +"' and m.post like '%" + searchp +"%'";
					//String search = "SELECT m FROM Mblog m where m.post like '%" + searchp +"%'";
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
	      request.setAttribute("name", uname);
	      request.setAttribute("moto", umoto);
	      request.setAttribute("jd", ujoindate);
	      
	      
	      
	      
	      getServletContext()
	      	.getRequestDispatcher("/Userprofiledisp.jsp")
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
