package com.sample;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
/**
 * This is a sample file to launch a process.
 */
public class HelloProcessExtension implements WorkItemHandler {
	Client client = Client.create();
	
	 public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
			manager.abortWorkItem(workItem.getId());
		  }
	 
	

	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		
		
		System.out.println(workItem.getParameter("param1") + "\n" 
		                  +workItem.getParameter("param2") + "\n" 
		                  +workItem.getParameter("param3") );
	    manager.completeWorkItem(workItem.getId(), null);
	    
	  
	    		System.out.println("Calling Webservices:");
	    		 String url ="http://127.0.0.1:8080/alfresco/service/api/mimetypes/descriptions";
						
						WebResource webResource = client.resource(url);
						ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
						if (response.getStatus() != 200) {
							throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
						}
	           
						JSONObject jsonOutput = (JSONObject) JSONSerializer.toJSON(response.getEntity(String.class));
						System.out.println(jsonOutput.toString());
	    		       
	    		    
	    
	    //
	  }
	
	
}