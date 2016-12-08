package oose.gramr.Set.Presentation.Controller;

import oose.gramr.Exceptions.ParameterException;
import oose.gramr.Photo.Domain.Photo;
import oose.gramr.Photo.Presentation.Model.PhotoModel;
import oose.gramr.Photo.Presentation.Model.PhotoSearchModel;
import oose.gramr.Photo.Services.IPhotoService;
import oose.gramr.Set.Services.ISetService;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Page controller to add a photo to a set
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
 * - De gebruiker kan een foto aan een set toevoegen
 */
@Singleton
@WebServlet(urlPatterns = "/addphototoset")
public class SetAddPhotoController extends HttpServlet {

    @Inject
    private ISetService setService;

    @Inject
    private IPhotoService photoService;

    /**
     * shows addphototoset.jsp with all the users photo's so he/she can add them to a set
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("titel") != null && !request.getParameter("titel").equals("")) {
            try {
                PhotoSearchModel photoSearchModel = new PhotoSearchModel(photoService, request.getParameter("user"), request.getParameter("titel"));
                List<Photo> photos = photoSearchModel.getAllPhotos();
                request.setAttribute("items", photos);
                request.getRequestDispatcher("addphototoset.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("error", "An error has occured.." + e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            try {
                PhotoModel photoModel = new PhotoModel(photoService, request.getParameter("user"));
                List<Photo> items = photoModel.getAllPhotos();
                request.setAttribute("items", items);
                request.getRequestDispatcher("addphototoset.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("error", "An error has occured.." + e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }

    /**
     * adds the photo to a set given the users input through the client.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (!request.getParameter("setID").equals("") && !request.getParameter("photoID").equals("")) {

                int setID = Integer.parseInt(request.getParameter("setID"));
                int photoID = Integer.parseInt(request.getParameter("photoID"));
                String isPrivate = request.getParameter("isPrivacy");

                if (isPrivate != null) {
                    setService.addPhotoToSet(setID, photoID, true);
                } else {
                    setService.addPhotoToSet(setID, photoID, false);
                }

                request.getRequestDispatcher("succes.jsp").forward(request, response);

            } else {
                throw new ParameterException("Incorrect parameters");
            }
        } catch (Exception e) {
            request.setAttribute("error", "An error has occured.. " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }
}
