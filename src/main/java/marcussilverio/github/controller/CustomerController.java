package marcussilverio.github.controller;

import marcussilverio.github.dto.CustomerDto;
import marcussilverio.github.dto.Error;
import marcussilverio.github.service.CustomerService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerController {
  @Inject
  CustomerService service;
  @GET
  public List<CustomerDto> findAllCustomers(){
    return service.findAllCustomers();
  }

  @GET
  @Path("{id}")
  public CustomerDto findCustomerById(@PathParam("id") Long id){
    return service.findCustomerById(id);
  }
  @POST
  @Transactional
  public Response saveCustomer(CustomerDto dto){
    try{
      service.createNewCustomer(dto);
      return Response.status(Response.Status.CREATED).build();
    }catch (Exception err) {
      err.printStackTrace();
      if(err.getMessage().equals("Constraint violation error"))
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("Field required not provided")).build();
      return Response.serverError().build();
    }
  }
  @PUT
  @Path("{id}")
  @Transactional
  public Response changeCustomer(@PathParam("id") Long id, CustomerDto dto){
    try{
      service.changeCustomer(id, dto);
      return Response.accepted().build();
    }catch (Exception err){
      err.printStackTrace();
      if(err.getMessage().equals("Not Found"))
        return Response.status(Response.Status.NOT_FOUND).build();
      if(err.getMessage().equals("Constraint violation error"))
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("Field required not provided")).build();
      return Response.serverError().entity(err.getMessage()).build();
    }
  }
  @DELETE
  @Path("{id}")
  @Transactional
  public Response deleteCustomer(@PathParam("id") Long id){
    try{
      service.deleteCustomer(id);
      return Response.accepted().build();
    }catch (Exception err){
      err.printStackTrace();
      return Response.serverError().build();
    }
  }
}
