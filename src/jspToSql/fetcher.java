package jspToSql;


import java.io.IOException; 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;

import jspToSql.UserData;
import jspToSql.Names;
// Import Database Connection Class file 
//import jspToSql.DatabaseConnection;

@WebServlet("/")

public class fetcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserData userDAO;
	
	public void init() {
		userDAO = new UserData();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/edit":
				showEditForm(request , response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		List<Names> listUser = userDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("resultList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));
		Names existingUser = userDAO.selectUser(id);
		System.out.println(existingUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		Names newUser = new Names( id,name, address);
		userDAO.insertUser(newUser);
		response.sendRedirect("resultList");
	}

	

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
	
		String address = request.getParameter("address");

		Names book = new Names(id, name, address);
		userDAO.updateUser(book);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect("list");

	}

}




























//public class fetcher extends HttpServlet {
//	
//	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
//	{	
//		PrintWriter out = res.getWriter();
//	
//		
//		String value = req.getParameter("holder");
//		try {
//			Connection conn = DatabaseConnection.initializeDatabase();
//			if(value.equals("all")){
//				String query = "select * from u_data";
//				PreparedStatement stmt = conn.prepareStatement(query);
//				ResultSet rs = stmt.executeQuery();
//				while(rs.next()) {
//				
//				out.println(rs.getString("u_id"));
//				out.println(rs.getString("u_name"));
//				out.println(rs.getString("u_address"));
//				
//				}
//			stmt.close();
//			conn.close();
//			}
//			else if(value.equals("name")){
//				String query = "select u_name from u_data";
//				PreparedStatement stmt = conn.prepareStatement(query);
//				ResultSet rs = stmt.executeQuery();
//				while(rs.next()) {
//					
//					out.println(rs.getString("u_name"));
//	
//					}
//				stmt.close();
//				conn.close();
//			}
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//
//}