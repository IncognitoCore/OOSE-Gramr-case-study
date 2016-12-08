package oose.gramr.Set.Presentation.Controller;

import oose.gramr.Exceptions.ParameterException;
import oose.gramr.Set.Services.ISetService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Page controller to show editset.jsp
 *
 * This class covers the following requirements:
 * - De front-end en back-end van de applicatie moeten samen in 1 container kunnen draaien zodat ze lokaal met elkaar kunnen communiceren.
 * - De applicatie maakt gebruik van de volgende APIs en frameworks:
     JSP
     Servlet v3.0
     JAX-RS v2.0 (REST, JSON)
     Jersey v2.17 (REST)
     Guice v4.0 (Dependency Injection)
     JDBC
     JDBC driver v5.1.34 voor MySQL
 * - Views (JSP-bestanden) bevatten geen Java-code, alleen JSP of JSTL markup.
 * - PageControllers (Servlets) bevatten geen markup, alleen Java-code.
 * - De gebruiker kan de naam van een set aanpassen
 */
public class EditSetPageController extends HttpServlet {

    @Inject
    private ISetService setService;

    /**
     * show editset.jsp
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("editset.jsp").forward(request, response);
    }

    /**
     * edits the name of a set given the users input through the client.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(!request.getParameter("setId").equals("") && !request.getParameter("name").equals("")) {
                int setId = Integer.parseInt(request.getParameter("setId"));
                String name = request.getParameter("name");

                setService.saveSet(setId, name);

                request.getRequestDispatcher("succes.jsp").forward(request, response);
            }else {
                throw new ParameterException("Incorrect parameters");
            }
        } catch (Exception e) {
            request.setAttribute("error", "An error has occured.. " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
