package in.sp.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginForm")
public class Login extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException , IOException{
	resp.setContentType("text/html");
	PrintWriter out = resp.getWriter();
	
	String myemail = req.getParameter("email");
	String mypass = req.getParameter("pass");
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}catch(ClassNotFoundException e) {
		System.out.println(e.getMessage());
	}
	
	try {
		String url = "jdbc:mysql://localhost:3306/yt_demo";
		String username = "root";
		String password = "1234";
		Connection con = DriverManager.getConnection(url, username, password);
		
		PreparedStatement ps = con.prepareStatement("SELECT * FROM register WHERE email=? AND password=?");
		ps.setString(1, myemail);
		ps.setString(2, mypass);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()){

			HttpSession session = req.getSession();
			session.setAttribute("session_name", rs.getString("name"));
			session.setAttribute("session_email", rs.getString("email"));
			
			RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
			rd.include(req, resp);
		}else {
			out.print("<h3 style='color: red'> Email and Password didn't matched</h3>");
			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			rd.include(req, resp);
		}
		
	}catch(Exception e){
		e.printStackTrace();
		
		out.print("<h3 style='color: red'>"+ e.getMessage() +"</h3>");
		
		RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
		rd.include(req, resp);
	}
}
}