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
import java.sql.ResultSet;
import java.sql.SQLException;
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 RequestDispatcher dispatcher = null;
	        Connection con = null;
	        response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
	        out.println("<html><body>");
	        out.println("<h2>Income and Expense Report</h2>");
	        out.println("<table border='1'>");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PersonalFinanceTracker?useSSL=false","Anjana","12345");
				PreparedStatement stmt1 = con.prepareStatement("SELECT source,amount,dt FROM income");
	            PreparedStatement stmt2 = con.prepareStatement("SELECT source,amount,dt FROM expense");
	            ResultSet rs1 = stmt1.executeQuery();
	            ResultSet rs2 = stmt2.executeQuery();
	            while (rs1.next() && rs2.next()) {
	                String incSource = rs1.getString("source");
	                double incAmount = rs1.getDouble("amount");
	                String incDate = rs1.getString("dt");
	                String expSource = rs2.getString("source");
	                double expAmount = rs2.getDouble("amount");
	                String expDate = rs2.getString("dt");
	              
	                out.println("<tr><th>Source</th><th>Amount</th><th>Date</th></tr>");
	                out.println("<tr><td>" + incSource + "</td><td>" + incAmount + "</td><td>" + incDate + "</td></tr><br>");
	                
	                out.println("<tr><th>Source</th><th>Amount</th><th>Date</th></tr>");
	                out.println("<tr><td>" + expSource + "</td><td>" + expAmount + "</td><td>" + expDate + "</td></tr>");
	            }
	            stmt1.close();
	            stmt2.close();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(con != null) {
						con.close();
					}	
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	       
		}
	

}
