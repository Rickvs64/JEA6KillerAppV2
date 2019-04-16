package Servlets.Secured;

import JWT.AuthenticationControllerProvider;
import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.SessionUtils;
import com.auth0.Tokens;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet(urlPatterns = {"/callback"})
public class Secured_CallbackServlet extends HttpServlet {

    private String redirectOnSuccess;
    private String redirectOnFail;
    private AuthenticationController authenticationController;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        redirectOnSuccess = "/JEA6KillerAppV2/portal/home";
        redirectOnFail = "/JEA6KillerAppV2/login";

        try {
            authenticationController = AuthenticationControllerProvider.getInstance(config);
        } catch (UnsupportedEncodingException e) {
            throw new ServletException("Couldn't create the AuthenticationController instance. Check the configuration.", e);
        }
    }

    /**
     * Process a call to the redirect_uri with a GET HTTP method.
     *
     * @param req the received request with the tokens (implicit grant) or the authorization code (code grant) in the parameters.
     * @param res the response to send back to the server.
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        handle(req, res);
    }


    /**
     * Process a call to the redirect_uri with a POST HTTP method. This occurs if the authorize_url included the 'response_mode=form_post' value.
     * This is disabled by default. On the Local Servlet scope you can specify the 'com.auth0.allow_post' parameter to enable this behaviour.
     *
     * @param req the received request with the tokens (implicit grant) or the authorization code (code grant) in the parameters.
     * @param res the response to send back to the server.
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        handle(req, res);
    }

    private void handle(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            Tokens tokens = authenticationController.handle(req);
            SessionUtils.set(req, "accessToken", tokens.getAccessToken());
            SessionUtils.set(req, "idToken", tokens.getIdToken());
            res.sendRedirect(redirectOnSuccess);
        } catch (IdentityVerificationException e) {
            e.printStackTrace();
            res.sendRedirect(redirectOnFail);
        }
    }

}