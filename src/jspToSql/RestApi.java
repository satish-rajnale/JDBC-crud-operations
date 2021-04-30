package jspToSql;

import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import org.json.JSONArray;
import org.json.JSONObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import javax.ws.rs.core.MediaType;
@MultipartConfig
@WebServlet("/RestApi/*")
public class RestApi  extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
			
			String requestUrl = req.getRequestURI();
			String url = requestUrl;
			String[] list = url.split("/");
			String name = list[3].toString();
			String query = "";
			
			PrintWriter out = res.getWriter();
			try {			
				Connection conn = DatabaseConnection.initializeDatabase();
				query = "select * from u_data where u_name="+"\""+name+"\"";
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				
				JSONObject jsonObject = new JSONObject();
				JSONArray array = new JSONArray();
				
				while(rs.next()) {
					JSONObject record = new JSONObject();
			         //Inserting key-value pairs into the json object
			         record.put("ID", rs.getInt("u_id"));
			         record.put("First_Name", rs.getString("u_name"));
			         record.put("Address", rs.getString("u_address"));
			         
			         array.put(record);
			      }
			      jsonObject.put("Users_data", array);		
			      out.println(jsonObject.toString(4));
				stmt.close();
				conn.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
	}
//	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
////	    String orderNumber = req.getParameter("testString");
////        System.out.println(orderNumber);
////        res.getWriter().print(orderNumber);
//		
//		String name = req.getParameter("Name");
//	    
//		String query = "";
//		
//		PrintWriter out = res.getWriter();
//		try {			
//			Connection conn = DatabaseConnection.initializeDatabase();
//			query = "select * from u_data where u_name="+"\""+name+"\"";
//			PreparedStatement stmt = conn.prepareStatement(query);
//			ResultSet rs = stmt.executeQuery();
//			
//			JSONObject jsonObject = new JSONObject();
//			JSONArray array = new JSONArray();
//			
//			while(rs.next()) {
//				JSONObject record = new JSONObject();
//		         //Inserting key-value pairs into the json object
//		         record.put("ID", rs.getInt("u_id"));
//		         record.put("First_Name", rs.getString("u_name"));
//		         record.put("Address", rs.getString("u_address"));
//		         
//		         array.put(record);
//		      }
//		      jsonObject.put("Users_data", array);	
//		  
//		      out.println(jsonObject.toString(4));
//			stmt.close();
//			conn.close();
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//}
//	
//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//	public String sayPlainTextHello() {
//		return "Hello World RESTful Jersey!";
//	}

//	@GET
//	@Produces(MediaType.TEXT_XML)
//	public String sayXMLHello() {
//		return "<?xml version=\"1.0\"?>" + "<hello> Hello World RESTful Jersey"
//				+ "</hello>";
//	}
//
//	@GET
//	@Path("hello")
//	@Produces(MediaType.TEXT_HTML)
//	public String sayHtmlHello() {
//		return "<html> " + "<title>" + "Hello World RESTful Jersey"
//				+ "</title>" + "<body><h1>" + "Hello World RESTful Jersey"
//				+ "</body></h1>" + "</html> ";
//	}

//	@POST
//	@Produces(MediaType.TEXT_XML)
//	public String postIt(Names a1) {
//		System.out.println(a1);
//		repo.getNames(a1);
//		return a1;
//	}

}


