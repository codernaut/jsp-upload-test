package com.gul.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Download
 */
@WebServlet("/download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Download() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			String recordNumber = request.getParameter("number");
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager
					.getConnection("jdbc:mysql://localhost/ggxlogs?" + "user=root&password=Lmkr@123");
			ResultSet rs = conn.createStatement().executeQuery("select * from new_table");
			for (int i = 0; i < Integer.parseInt(recordNumber); i++)
				rs.next();
			byte[] fileBytes = rs.getBytes(1);
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition","attachment; filename=\"abc.txt+\"");
			response.setHeader("cache-control", "no-cache");
			ServletOutputStream outs = response.getOutputStream();
			outs.write(fileBytes);
			outs.flush();
			outs.close(); 

			// response.setContentType("application/octet-stream");

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
