package jspToSql;

import java.io.IOException; 


public class Names {
	protected int id;
	protected String name;
	protected String address;
	

	public Names() {
	}
	
	public Names(String name, String address) {
		super();
		this.name = name;
		this.address= address;
	}

	public Names(int id, String name,String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Names [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

}























//
//import java.io.PrintWriter; 
//import java.sql.Connection; 
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//import javax.servlet.ServletException; 
//import javax.servlet.annotation.WebServlet; 
//import javax.servlet.http.HttpServlet; 
//import javax.servlet.http.HttpServletRequest; 
//import javax.servlet.http.HttpServletResponse; 
//public class Names extends HttpServlet {
//	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
//	{	
////		PrintWriter out = res.getWriter();
////	
////		try {
////			Connection conn = DatabaseConnection.initializeDatabase();
////			
////			
////				String query = "select u_name from u_data";
////				PreparedStatement stmt = conn.prepareStatement(query);
////				ResultSet rs = stmt.executeQuery();
////				while(rs.next()) {
////					
////					out.println(rs.getString("u_name"));
////	
////					}
////				stmt.close();
////				conn.close();
////			}
////		
////		catch(Exception e){
////			e.printStackTrace();
//		
////		}
//	}
//
//}
