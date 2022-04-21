package com.training.servlet;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.jdbc.PriceComparator;
import com.training.jdbc.Product;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    String name = null;
    
    public void init() {
    	 name = "Jacob";
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//			response.setContentType("text/html");
//			PrintWriter out = response.getWriter();
//			out.println("<HTML>");
//			out.println("<HEAD><TITLE>Servlet demo </TITLE></HEAD>");
//			out.println("<BODY>");
//			out.println("<H1> Hello from Servlet: </H1>" + name);
//			out.println("</BODY></HTML>");
			System.out.println("Inside of doGet method...");
			
			//use when we want to redirect to external component
			//response.sendRedirect("home.jsp");
			
			//to  do within same project, create object of request dispatcher.
//			request.setAttribute("userName", name);
//			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
//			rd.forward(request, response);
			
			String userName = request.getParameter("uname");
			String password = request.getParameter("pwd");
			request.setAttribute("userName", userName);
			
			//database connection//to load the driver
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
			
			String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=mars;"+  "encrypt= true; trustServerCertificate = true";
			String user = "sa";
			String DBpassword = "19Lmar71";
			
			Connection con;
			try {
				con = DriverManager.getConnection(url, user, DBpassword);
				System.out.println("!!!Connected to the database!!!");
				
				
				String query = "select * from uNamePwdList";
				PreparedStatement pstmt = con.prepareStatement(query);	
				
				ResultSet namepwdList = pstmt.executeQuery();
				List <NamePwd> NamePwdList = new ArrayList<NamePwd>();
				int ValidCredentials = 0;
				
				while (namepwdList.next()){
					NamePwd p1 = new NamePwd(namepwdList.getString("userName"), namepwdList.getString("pwd"));
					NamePwdList.add(p1);
				}
			
				for (NamePwd loginSet: NamePwdList) {
					System.out.println(" Login Set:: Name: " + loginSet.getName()+ "   PWD: " + loginSet.getPwd());
					if (userName.equalsIgnoreCase(loginSet.getName()) && password.equalsIgnoreCase(loginSet.getPwd())  ) {
						ValidCredentials = 1;
						RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
						rd.forward(request, response);
					}
				
				}
				//didn't find a match
				if (ValidCredentials == 0){
					System.out.println("Invalid credentials");
					RequestDispatcher rd = request.getRequestDispatcher("invalid.jsp");
					rd.forward(request, response);
				}
			}
			catch (SQLException e) {
		
				e.printStackTrace();
			}
		

			
//			if (userName.equalsIgnoreCase("Lynn") && password.contentEquals("1234")  ) {
//				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
//				rd.forward(request, response);
//			}
//			else {
//				System.out.println("Invalid credentials");
//				RequestDispatcher rd = request.getRequestDispatcher("invalid.jsp");
//				rd.forward(request, response);
//			}
//			
			
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inside of doPost ...");
		doGet(request, response);
	}

}
