package API;

import Domains.Account;
import Domains.Participant;
import Repositories.AccountRepo;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/account")
public class AccountAPI {
    @Inject
    private AccountRepo accRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccounts(@Context UriInfo uriInfo) {
        JSONObject response = new JSONObject();
        response.put("result", accRepo.getAllAccounts());
        response.put("_links", getAllLinks(uriInfo));
        return Response.ok(response.toString()).build();
    }

    @Path("/{ID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountById(@Context UriInfo uriInfo, @PathParam("ID") Integer id) {
        JSONObject response = new JSONObject();
        response.put("result", accRepo.getAccountById(id).toJSON());
        response.put("_links", getAllLinks(uriInfo));
        response.put("_otherlinks", getRelevantLinks(uriInfo, id));
        return Response.ok(response.toString()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAccount(@Context UriInfo uriInfo, Account acc) {
        JSONObject response = new JSONObject();

        if (accRepo.addAccount(acc)) {
            response.put("result", acc.getName() + " has been added.");
        }
        else {
            response.put("result", acc.getName() + " could NOT be added.");
        }

        response.put("_links", getAllLinks(uriInfo));
        response.put("_otherlinks", getRelevantLinks(uriInfo, acc.getId()));
        return Response.ok(response.toString()).build();
    }

    @Path("/help")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response help(@Context UriInfo uriInfo) {
        JSONObject response = new JSONObject();
        response.put("links", getAllLinks(uriInfo));
        return Response.ok(response.toString()).build();
    }

    /**
     * Get all links (url's)
     * Based on Alex Kir's manual implementation
     * @return Map of all links on this endpoint
     */
    private Map<String, String> getAllLinks(UriInfo uriInfo) {
        Map<String, String> links = new HashMap<>();
        String base = uriInfo.getBaseUri().toString() + "account/";
        links.put("GET all", base);
        links.put("GET by id", base + "[ID]");
        links.put("POST new acccount", base);

        return links;
    }

    /**
     * Get relevant links from other endpoints
     * todo: fill with methods from other endpoints once those are implemented
     * @Return Map of all relevant links from other endpoints
     */
    private Map<String, String> getRelevantLinks(UriInfo uriInfo, Integer accountId) {
        Map<String, String> links = new HashMap<>();

        String base = uriInfo.getBaseUri().toString() + "account/";
        // now fill it with methods from other restpoints
        // use accountId so they're already relevant for this particular account

        return links;
    }
}
