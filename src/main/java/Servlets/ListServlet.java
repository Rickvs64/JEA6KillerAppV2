package Servlets;

import Domains.Account;
import Repositories.AccountRepo;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="ListServlet", urlPatterns="/account")
public class ListServlet extends HttpServlet {

    @EJB
    AccountRepo accRepo;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Account acc = new Account();
        // acc.setName("Test account");
        // accRepo.addAccount(acc);
        request.setAttribute("accounts", accRepo.getAllAccounts());

        RequestDispatcher view = request.getRequestDispatcher("list.jsp");
        view.forward(request, response);
    }
}
