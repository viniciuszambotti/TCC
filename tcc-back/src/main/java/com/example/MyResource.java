package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



/**
 * Root resource (exposed at "myresource" path)
 */
@Path("Analysis")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("test")
    public String getIt() {
    	Client client = ClientBuilder.newClient();
    	String result = client.target("http://localhost:8084/").request().get(String.class);
    	System.out.println(result);

    	

       return "Got it!";
    }
}
