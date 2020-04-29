package be.pxl.student.REST;

import be.pxl.student.REST.resource.AccountResource;
import be.pxl.student.REST.resource.PaymentResource;
import be.pxl.student.dao.JPA.AccountDaoJPA;
import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import be.pxl.student.service.AccountService;
import be.pxl.student.service.AccountServiceJPA;
import be.pxl.student.service.PaymentService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//localhost:8080/budgetPlanner/api/accounts
@Path("/accounts")
public class AccountsRest {
    @Inject
    private AccountService accountService;
    @Inject
    private PaymentService paymentService;
    @Inject
    private AccountServiceJPA accountServiceJPA;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountResource accountResource) throws ClassNotFoundException {
        //start driver manueel als je jdbc gebruikt -> anders: no driver for jdbc
        Class.forName("com.mysql.jdbc.Driver");
        try {
            accountService.createAccount(mapToAccount(accountResource));
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @POST
    @Path("{accountName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPayment(PaymentResource paymentResource, @PathParam("accountName") String name) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            paymentService.createPayment(paymentResource, name);
        } catch (Exception e) {
            Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @GET
    @Path("{accountName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPaymentsByAccountName(@PathParam("accountName") String name) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        List<Payment> payments = null;

        try {
            payments = paymentService.getAllPayments(name);
        } catch (Exception e) {
            Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
        return Response.ok(payments).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccounts() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        List<Account> accounts = null;
        AccountService accountService = new AccountService();

        try {
            accounts = accountService.getAllAccounts();
        } catch (Exception e) {
            Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
        return Response.ok(accounts).build();
    }

    //Dit is puur om te testen met JPA
    @GET
    @Path("getall")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccountsByJPA() {
        List<Account> accounts = null;

        try {
            accounts = accountServiceJPA.getAllAccounts();
        } catch (Exception e) {
            Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
        return Response.ok(accounts).build();
    }

    private Account mapToAccount(AccountResource accountResource) {
        return new Account(accountResource.getIBAN(), accountResource.getName());
    }
}
