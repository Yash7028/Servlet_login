package in.sp.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/regForm")
public class Register extends HttpServlet {
@Override	
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException {
	
	PrintWriter out = resp.getWriter();
	
	String myname = req.getParameter("name1");
	String myemail = req.getParameter("email1");
	String mypass = req.getParameter("pass1");
	String mygender = req.getParameter("gender1");
	String mycity = req.getParameter("city1");
	try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded successfully");
    }catch(ClassNotFoundException e){
        System.out.println(e.getMessage());
    }
	try {
		 String username = "root";
	     String password = "1234";
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yt_demo", username,password);
        
        PreparedStatement ps = con.prepareStatement("insert into register values(? ,?, ?, ?, ? )");
        ps.setString(1,myname);
        ps.setString(2,myemail);
        ps.setString(3, mygender);
        ps.setString(4, mypass);
        ps.setString(5, mycity);
        
        int count =  ps.executeUpdate();
        if(count  > 0) {
        	resp.setContentType("text/html");
        	out.print("<h3 style= 'color:green'> User register successfully</h3>");
        	RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
        	rd.include(req, resp);
        }else {
        	resp.setContentType("text/html");
        	out.print("<h3 style= 'color:red'> User not register successfully</h3>");
        	RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
        	rd.include(req, resp);
        }
	}catch(Exception e) {
		e.printStackTrace();
		resp.setContentType("text/html");
    	out.print("<h3 style= 'color:red'>" + e.getMessage()+"</h3>");
    	RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
    	rd.include(req, resp);
	}
			
}
}
