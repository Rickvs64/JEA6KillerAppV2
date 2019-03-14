package API;

import Domains.Account;
import Repositories.AccountRepo;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/account")
public class AccountAPI {
    @Inject
    private AccountRepo accRepo;

    @GET
    public List<Account> getAllAccounts() {
        return accRepo.getAllAccounts();
    }

    @Path("/{ID}")
    @GET
    public Account getAccountById(@PathParam("ID") Integer id) {
        return accRepo.getAccountById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addAccount(Account acc) {
        // We will return a custom string
        String response = "";

        if (accRepo.addAccount(acc)) {
            response = acc.getName() + " has been added.";
        }
        else {
            response = acc.getName() + " could NOT be added.";
        }

        return response;
    }
}
