package com.personalFinanceTracker;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String upwd = request.getParameter("pass");
		int umob = Integer.parseInt(request.getParameter("contact"));
		
		out.print(uname);
		out.print(uemail);
		out.print(upwd);
		out.print(umob);
		Connection con = null;
		RequestDispatcher dispatcher = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PersonalFinanceTracker?useSSL=false","Anjana","12345");
			PreparedStatement pst = con.prepareStatement("insert into Register(uname,upwd,uemail,umob) values(?,?,?,?)");
			pst.setString(1,uname);
			pst.setString(2,upwd);
			pst.setString(3,uemail);
			pst.setInt(4,umob);
			
			int rowCount = pst.executeUpdate();
			dispatcher =request.getRequestDispatcher("login.jsp");
			if(rowCount > 0) {
				request.setAttribute("status","sucess");
			}else {
				request.setAttribute("status","failed");
			}
			dispatcher.forward(request,response);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
