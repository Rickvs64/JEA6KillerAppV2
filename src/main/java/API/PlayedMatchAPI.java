package API;

import Domains.Gamemode;
import Domains.PlayedMatch;
import Repositories.GamemodeRepo;
import Repositories.PlayedMatchRepo;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/match")
public class PlayedMatchAPI {

    @Inject
    private PlayedMatchRepo matchRepo;

    // Try to use this method sparingly as PlayedMatch will be the most persisted class
    @GET
    public List<PlayedMatch> getAllMatches() {
        return matchRepo.getAllMatches();
    }

    @Path("/{ID}")
    @GET
    public PlayedMatch getMatchById(@PathParam("ID") Integer id) {
        return matchRepo.getMatchById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addMatch(PlayedMatch match) {
        // We will return a custom string
        String response = "";

        if (matchRepo.addMatch(match)) {
            response = "New match has been added.";
        }
        else {
            response = "New match could NOT be added.";
        }

        return response;
    }

    @Path("/help")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response help(@Context UriInfo uriInfo) {
        JSONObject response = new JSONObject();
        response.put("links", getAllLinks(uriInfo));
        return Response.ok(response.toString()).build();
    }

    @Path("/byaccount/{ID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlayedMatch> getMatchesById(@PathParam("ID") Integer accid) {
        return matchRepo.getMatchesByAccountId(accid);
    }


    /**
     * Get all links (url's)
     * @return Map of all links on this endpoint
     */
    private Map<String, String> getAllLinks(UriInfo uriInfo) {
        Map<String, String> links = new HashMap<>();

        String base = uriInfo.getBaseUri().toString() + "match/";
        links.put("GET all", base);
        links.put("GET by id", base + "[ID]");
        links.put("POST new match", base);

        return links;
    }

}
