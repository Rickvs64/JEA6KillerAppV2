package API;

import Domains.Hero;
import Domains.Participant;
import Repositories.HeroRepo;
import Repositories.ParticipantRepo;
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

@Path("/participant")
public class ParticipantAPI {

    @Inject
    private ParticipantRepo participantRepo;

    @GET
    public List<Participant> getAllParticipant() {
        return participantRepo.getAllParticipants();
    }

    @Path("/{ID}")
    @GET
    public Participant getParticipantById(@PathParam("ID") Integer id) {
        return participantRepo.getParticipantById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addParticipant(Participant participant) {
        // We will return a custom string
        String response = "";

        if (participantRepo.addParticipant(participant)) {
            response = "Participant has been added.";
        }
        else {
            response = "Participant could NOT be added.";
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

        String base = uriInfo.getBaseUri().toString() + "participant/";
        links.put("GET all", base);
        links.put("GET by id", base + "[ID]");
        links.put("POST new participant", base);

        return links;
    }

}
