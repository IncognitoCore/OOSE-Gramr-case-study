package oose.gramr.Photo.Presentation.Controller;

import oose.gramr.Exceptions.ParameterException;
import oose.gramr.Photo.Domain.Photo;
import oose.gramr.Photo.Services.IPhotoService;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Page controller to add a photo
 * <p/>
 * This class covers the following requirements:
 * - De front-end en back-end van de applicatie moeten samen in 1 container kunnen draaien zodat ze lokaal met elkaar kunnen communiceren.
 * - De applicatie maakt gebruik van de volgende APIs en frameworks:
 * <p/>
 * JSP
 * Servlet v3.0
 * JAX-RS v2.0 (REST, JSON)
 * Jersey v2.17 (REST)
 * Guice v4.0 (Dependency Injection)
 * JDBC
 * JDBC driver v5.1.34 voor MySQL
 * - Views (JSP-bestanden) bevatten geen Java-code, alleen JSP of JSTL markup.
 * - PageControllers (Servlets) bevatten geen markup, alleen Java-code.
 */
@Singleton
@WebServlet(urlPatterns = "/addphoto")
public class AddPhotoPageController extends HttpServlet {

    @Inject
    private IPhotoService photoService;

    /**
     * show addphoto.jsp
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("addphoto.jsp").forward(request, response);
    }

    /**
     * Adds a photo to the database given the users input through the client.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("title") != null && !request.getParameter("title").equals("")
                    && request.getParameter("creator") != null && !request.getParameter("creator").equals("")
                    && request.getParameter("description") != null && !request.getParameter("description").equals("")
                    && request.getParameter("description") != null && !request.getParameter("url").equals("")) {
                String title = request.getParameter("title");
                String creator = request.getParameter("creator");
                String description = request.getParameter("description");
                String url = request.getParameter("url");
                photoService.addPhoto(new Photo(title, creator, description, url));
                request.getRequestDispatcher("succes.jsp").forward(request, response);
            } else {
                throw new ParameterException("Wrong parameters");
            }
        } catch (Exception e) {
            request.setAttribute("error", "An error has occured..." + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
