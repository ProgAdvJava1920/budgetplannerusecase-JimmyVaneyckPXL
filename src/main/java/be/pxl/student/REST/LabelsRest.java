package be.pxl.student.REST;

import be.pxl.student.REST.resource.AccountResource;
import be.pxl.student.REST.resource.LabelResource;
import be.pxl.student.entity.Label;
import be.pxl.student.service.AccountService;
import be.pxl.student.service.LabelService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/labels")
public class LabelsRest {
    private LabelService labelService = new LabelService();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLabels(LabelResource labelResource) {
        try {
            labelService.createLabel(labelResource);
        } catch (Exception e) {
            Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLabels() {
        List<Label> labels = labelService.getAllLabels();
        return Response.ok(labels).build();
    }

    @DELETE
    @Path("{id}")
    public Response removeLabel(@PathParam("id") int labelId) {
        try {
            labelService.deleteLabel(labelId);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }
}
