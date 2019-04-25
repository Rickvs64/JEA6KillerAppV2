package API;

import Domains.Hero;
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

@Path("/hero")
public class HeroAPI {

    @Inject
    private HeroRepo heroRepo;

    @GET
    public List<Hero> getAllHeroes() {
        return heroRepo.getAllHeroes();
    }

    @Path("/{ID}")
    @GET
    public Hero getHeroById(@PathParam("ID") Integer id) {
        return heroRepo.getHeroById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addHero(Hero hero) {
        // We will return a custom string
        String response = "";

        if (heroRepo.addHero(hero)) {
            response = hero.getName() + " has been added.";
        }
        else {
            response = hero.getName() + " could NOT be added.";
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
     * Based on Alex Kir's manual implementation
     * @return
     */
    private Map<String, String> getAllLinks(UriInfo uriInfo) {
        Map<String, String> links = new HashMap<>();

        String base = uriInfo.getBaseUri().toString() + "hero/";
        links.put("GET all", base);
        links.put("GET by id", base + "[ID]");
        links.put("POST new hero", base);

        return links;
    }

}
