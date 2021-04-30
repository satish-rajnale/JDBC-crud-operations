package jspToSql;

import java.net.URI;

import javax.ws.rs.client.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;



public class jerseyClient {
	private static final String webServiceURI = "http://localhost:8080/jspToSql_Project";

	public static void main(String[] args) {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		URI serviceURI = UriBuilder.fromUri(webServiceURI).build();
		WebTarget webTarget = client.target(serviceURI);

		// response
		System.out.println(webTarget.path("/*").path("RestApi").request()
				.accept(MediaType.TEXT_PLAIN).get(Response.class).toString());

		// text
		System.out.println(webTarget.path("/*").path("RestApi").request()
				.accept(MediaType.TEXT_PLAIN).get(String.class));

		// xml
		System.out.println(webTarget.path("rest").path("RestApi").request()
				.accept(MediaType.TEXT_XML).get(String.class));

		// html
		System.out.println(webTarget.path("/*").path("RestApi").request()
				.accept(MediaType.TEXT_HTML).get(String.class));
	}
}


	
