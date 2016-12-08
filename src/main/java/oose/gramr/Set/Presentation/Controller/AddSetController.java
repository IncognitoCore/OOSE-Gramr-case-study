package oose.gramr.Set.Presentation.Controller;

import oose.gramr.Exceptions.ParameterException;
import oose.gramr.Set.Services.ISetService;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Page controller to add a set
 * <p/>
 * This class covers the following requirements:
 * - De front-end en back-end van de applicatie moeten samen in 1 container kunnen draaien zodat ze lokaal met elkaar kunnen communiceren.
 * - De applicatie maakt gebruik van de volgende APIs en frameworks:
 * JSP
 * Servlet v3.0
 * JAX-RS v2.0 (REST, JSON)
 * Jersey v2.17 (REST)
 * Guice v4.0 (Dependency Injection)
 * JDBC
 * JDBC driver v5.1.34 voor MySQL
 * - Views (JSP-bestanden) bevatten geen Java-code, alleen JSP of JSTL markup.
 * - PageControllers (Servlets) bevatten geen markup, alleen Java-code.
 * - De gebruiker kan een set toevoegen.
 */
@Singleton
@WebServlet(urlPatterns = "/addset")
public class AddSetController extends HttpServlet {

    @Inject
    private ISetService setService;


    /**
     * show addset.jsp
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("addset.jsp").forward(request, response);
    }

    /**
     * Adds a set to the database given the users input through the client.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (!request.getParameter("name").equals("") && !request.getParameter("user").equals("")
                    && !request.getParameter("photos").equals("")) {
                String name = request.getParameter("name");
                String user = request.getParameter("user");
                String photos = request.getParameter("photos");
                String privates = request.getParameter("privates");

                setService.addSet(name, user, stringToIntArray(photos), stringToIntArray(privates));

                request.getRequestDispatcher("succes.jsp").forward(request, response);
            } else {
                throw new ParameterException("Incorrect parameters");
            }
        } catch (Exception e) {
            request.setAttribute("error", "An error has occured.. " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    /**
     * converst a comma separated list to a List of integers
     *
     * @param str
     * @return
     */
    private List<Integer> stringToIntArray(String str) {
        List<Integer> returnArr = new ArrayList<>();
        if (str != null && !str.equals("")) {
            String[] strings = str.split(",");
            for (String stringInt : strings) {
                returnArr.add(Integer.parseInt(stringInt));
            }
        }
        return returnArr;
    }

}
