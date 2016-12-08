package oose.gramr.Photo.Presentation.Controller;

import oose.gramr.Photo.Domain.Photo;
import oose.gramr.Photo.Presentation.Model.PhotoModel;
import oose.gramr.Photo.Services.IPhotoService;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Page Controller to view all photos
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
 */
@Singleton
@WebServlet(urlPatterns = "/photos")
public class PhotoViewPageController extends HttpServlet {

    @Inject
    private IPhotoService photoService;

    /**
     * shows all pictures with an option to apply a filter
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PhotoModel photoModel = new PhotoModel(photoService, request.getParameter("user"));
            List<Photo> photos = photoModel.getAllPhotos();
            request.setAttribute("photos", photos);
            request.getRequestDispatcher("photos.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "An error has occured.. " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

}

