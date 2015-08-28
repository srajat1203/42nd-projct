

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



@WebServlet("/rwmblogs")
public class rwmblogs extends HttpServlet {
	

	String err = "<div class=\"alert alert-danger\"> <strong>Error!</strong> You cannot have more than 50 chars </div>";
	String tmp = "";
	
	String users = "";

	String img = "";
	
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
		  
		  String uemail = (String) session.getAttribute("curuser");
		  
		  Mblog m = new Mblog();
		  m.setPost(post);
		  m.setUemail(uemail);
			
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
			String qString = "SELECT m FROM Mblog m order by m.id desc";
			
			TypedQuery<Mblog> q =  em.createQuery(qString, Mblog.class);
			
			List<Mblog> mblogs = q.getResultList();
			
			//Collections.reverse(mblogs);
			try {
				tmp = " ";
				users = " ";
				img = "";
				for (Mblog cur : mblogs) {
					
					String curr_user = cur.getUemail();
					String finduser = "SELECT u FROM Usr u where u.email='" + curr_user +"'";
					TypedQuery<Usr> usrq =  em.createQuery(finduser, Usr.class);
					Usr cuser = usrq.getSingleResult();
					String url = cuser.getImg();
					String cimg = getImage(url);
					
					
					tmp = tmp + cimg + cur.getPost() + "<br><br>"; 
					String cur_user = cur.getUemail();
					
					
					
					String qname = "SELECT u FROM Usr u where u.email = '"+ cur_user+"'";
					TypedQuery<Usr> u =  em.createQuery(qname, Usr.class);
					Usr user = u.getSingleResult();
					String uname = user.getName();
					
					users = users + "<a href=\"Userprofile?user=" + cur_user  + "\">" + uname + "</a><br><br><br>";
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
						users = " ";
						img = " ";
						for (Mblog cur : mblogs2) {
							
							String curr_user = cur.getUemail();
							String finduser = "SELECT u FROM Usr u where u.email='" + curr_user +"'";
							TypedQuery<Usr> usrq =  em.createQuery(finduser, Usr.class);
							Usr cuser = usrq.getSingleResult();
							String url = cuser.getImg();
							String cimg = getImage(url);
							
							tmp = tmp + cimg + cur.getPost() + "<br><br>"; 
							String cur_user = cur.getUemail();
							
							String qname = "SELECT u FROM Usr u where u.email = '"+ cur_user+"'";
							TypedQuery<Usr> u =  em.createQuery(qname, Usr.class);
							Usr user = u.getSingleResult();
							String uname = user.getName();
							
							users = users + "<a href=\"Userprofile?user=" + cur_user  + "\">" + uname + "</a><br><br><br>";
						}
					} catch (Exception e){
						e.printStackTrace();
					} finally {
						em.close();
					}
				}
				
			}
	
		
			
			
	//Bonus
			
			//String request.getParameter("image");
			
			
	//Bonus end
			
			
			
	      response.setContentType("text/html");
	      
	      request.setAttribute("tmp", tmp);
	      request.setAttribute("users", users);
	      request.setAttribute("image", img);
	      
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

	   
	  public String getImage(String url)
	  {
		  String img = "";
		  
		 img = "<img src="+url+" class=\"img-rounded\" alt=\"Cinque Terre\" width=\"40\" height=\"40\">";
		  
		  return img;
	  }
}
