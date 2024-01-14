package people.management.web.resource;
import java.net.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import people.management.web.data.*;
import people.management.web.service.*;
@Path("/peoples")
public class PeopleResource {
 PeopleService service = new PeopleService();
 @Context
 UriInfo uriInfo;
 
 @POST
 @Consumes(MediaType.APPLICATION_XML)
 @Produces(MediaType.APPLICATION_XML)
 public Response addPeople(People s) {
 People people = service.addPeople(s);
 if(people == null) {
 return Response.status(Response.Status.BAD_REQUEST).build();
 }
 URI uri = uriInfo.getRequestUri();
 String newUri = uri.getPath() + "/" + people.getId();
 return Response.status(Response.Status.CREATED)
 .entity(people)
 .contentLocation(uri.resolve(newUri))
 .build();
 }
 
 @DELETE
 @Path("/{id}")
 @Produces(MediaType.APPLICATION_XML)
 public Response deletePeoplet(@PathParam("id") int id) { 
 if(service.deletePeople(id) == false) {
 return Response.status(Response.Status.NOT_FOUND).build();
 }
 return Response.status(Response.Status.OK).build();
 }
 @GET
 @Path("/{id}")
 @Produces(MediaType.APPLICATION_XML)
 public Response getPeople(@PathParam("id") int id) {
 People people = service.getPeople(id);
 if(people == null) {
 return Response.status(Response.Status.NOT_FOUND).build();
 }
 Link link = Link.fromUri(uriInfo.getRequestUri())
		 .rel("self")
		 .type("application/xml")
		 .build();
		 return Response.status(Response.Status.OK)
		 .entity(people)
		 .links(link)
		 .build();
		 }
		 
		}