package be.pxl.student.REST;

import be.pxl.student.REST.resource.AccountResource;
import be.pxl.student.REST.resource.LabelResource;
import be.pxl.student.REST.resource.PaymentResource;
import be.pxl.student.service.AccountService;
import be.pxl.student.service.PaymentService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//localhost:8080/budgetPlanner/api/accounts
@Path("/accounts")
public class AccountsRest {
    private AccountService accountService = new AccountService();
    private PaymentService paymentService = new PaymentService();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountResource accountResource) {
        try {
            accountService.createAccount(accountResource);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @POST
    @Path("{accountName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPayment(PaymentResource paymentResource, @PathParam("accountName") String name) {
        try {
            paymentService.createPayment(paymentResource, name);
        } catch (Exception e) {
            Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }
}
