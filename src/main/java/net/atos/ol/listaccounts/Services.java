package net.atos.ol.listaccounts;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/b2crestapi")
public interface Services {

    @GET
    @Path("/{param}/principaltocashaccountlinks")
    @Produces(MediaType.APPLICATION_JSON)
    Response listAccounts(@Context HttpHeaders headers, @PathParam("param") String param);

  
}
