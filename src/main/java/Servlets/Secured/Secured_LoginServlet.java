package Servlets.Secured;

import JWT.AuthenticationControllerProvider;
import com.auth0.AuthenticationController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet(urlPatterns = {"/login"})
public class Secured_LoginServlet extends HttpServlet {

    private AuthenticationController authenticationController;
    private String domain;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        domain = config.getServletContext().getInitParameter("com.auth0.domain");
        try {
            authenticationController = AuthenticationControllerProvider.getInstance(config);
        } catch (UnsupportedEncodingException e) {
            throw new ServletException("Couldn't create the AuthenticationController instance. Check the configuration.", e);
        }
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        String redirectUri = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/JEA6KillerAppV2/callback";

        String authorizeUrl = authenticationController.buildAuthorizeUrl(req, redirectUri)
                .withAudience(String.format("https://JEA6KillerAppV2/CustomAPI", domain))
                .build();
        res.sendRedirect(authorizeUrl);
    }

}
