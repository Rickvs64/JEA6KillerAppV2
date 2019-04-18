package Servlets.Secured;

import okhttp3.HttpUrl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/logout"})
public class Secured_LogoutServlet extends HttpServlet {


    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }


        // Currently experiencing a problem with logging out
        // The first option logs out but doesn't return to my application
        // The second option only works on Chrome (or other 3rd party) accounts
        response.sendRedirect("https://dev-e03oe7vv.eu.auth0.com/v2/logout");
        // response.sendRedirect("/JEA6KillerAppV2/login");
    }

}