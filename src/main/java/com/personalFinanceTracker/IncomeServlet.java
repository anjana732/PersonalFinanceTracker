package com.personalFinanceTracker;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IncomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String source = request.getParameter("source");
		 String date = request.getParameter("date");
        double amount = Double.parseDouble(request.getParameter("amount"));
        RequestDispatcher dispatcher = null;
        Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PersonalFinanceTracker?useSSL=false","Anjana","12345");
			PreparedStatement pst = con.prepareStatement("insert into income(source,amount,dt) values(?,?,?)");
			pst.setString(1,source);
			pst.setDouble(2,amount);
			pst.setString(3,date);

			int rowCount = pst.executeUpdate();
			dispatcher =request.getRequestDispatcher("income.jsp");
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
				if(con != null) {
					con.close();
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
       
	}

}
