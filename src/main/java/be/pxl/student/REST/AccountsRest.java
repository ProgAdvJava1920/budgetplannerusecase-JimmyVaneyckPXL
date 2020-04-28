package be.pxl.student.REST;

import be.pxl.student.REST.resource.AccountResource;
import be.pxl.student.REST.resource.PaymentResource;
import be.pxl.student.dao.JPA.AccountDaoJPA;
import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import be.pxl.student.service.AccountService;
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
    private AccountDaoJPA accountDAOJPA;
    private PaymentService paymentService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountResource accountResource) {
        try {
            accountService.createAccount(mapToAccount(accountResource));
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

    @GET
    @Path("{accountName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPaymentsByAccountName(@PathParam("accountName") String name) {
        List<Payment> payments = null;

        try {
            payments = paymentService.getAllPayments(name);
        } catch (Exception e) {
            Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
        return Response.ok(payments).build();
    }

    //Dit is puur om te testen met JPA
    @GET
    @Path("getall")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPaymentsByJPA() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("budgetplanner_pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        AccountDaoJPA accountDAOJPA = new AccountDaoJPA(entityManager);
        List<Account> accounts = null;

        try {
            accounts = accountDAOJPA.getAll();
        } catch (Exception e) {
            Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
        return Response.ok(accounts).build();
    }

    private Account mapToAccount(AccountResource accountResource) {
        return new Account(accountResource.getIBAN(), accountResource.getName());
    }
}
