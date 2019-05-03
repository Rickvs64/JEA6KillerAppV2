package API;

import Domains.Gamemode;
import Domains.Hero;
import Repositories.GamemodeRepo;
import Repositories.HeroRepo;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/gamemode")
public class GamemodeAPI {

    @Inject
    private GamemodeRepo gamemodeRepo;

    @GET
    public List<Gamemode> getAllGamemodes() {
        return gamemodeRepo.getAllGamemodes();
    }

    @Path("/{ID}")
    @GET
    public Gamemode getGamemodeById(@PathParam("ID") Integer id) {
        return gamemodeRepo.getGamemodeById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addGamemode(Gamemode gamemode) {
        // We will return a custom string
        String response = "";

        if (gamemodeRepo.addGamemode(gamemode)) {
            response = gamemode.getName() + " has been added.";
        }
        else {
            response = gamemode.getName() + " could NOT be added.";
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

    /**
     * Get all links (url's)
     * @return Map of all links on this endpoint
     */
    private Map<String, String> getAllLinks(UriInfo uriInfo) {
        Map<String, String> links = new HashMap<>();

        String base = uriInfo.getBaseUri().toString() + "gamemode/";
        links.put("GET all", base);
        links.put("GET by id", base + "[ID]");
        links.put("POST new gamemode", base);

        return links;
    }

}
