package oose.gramr.Set.Presentation.Controller;

import oose.gramr.DatabaseConnection.DatabaseProperties;
import oose.gramr.Photo.DataSource.FilterDAO;
import oose.gramr.Photo.DataSource.IFilterDAO;
import oose.gramr.Photo.DataSource.IPhotoDAO;
import oose.gramr.Photo.DataSource.PhotoDAO;
import oose.gramr.Photo.Domain.Photo;
import oose.gramr.Photo.Presentation.Model.PhotoModel;
import oose.gramr.Photo.Services.IPhotoService;
import oose.gramr.Photo.Services.PhotoService;
import oose.gramr.Set.DataSource.ISetDAO;
import oose.gramr.Set.DataSource.SetDAO;
import oose.gramr.Set.Domain.Set;
import oose.gramr.Set.Presentation.Model.SetModel;
import oose.gramr.Set.Services.ISetService;
import oose.gramr.Set.Services.SetService;

import javax.ejb.Singleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * shows all sets
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
@WebServlet(urlPatterns = "/sets")
public class SetViewPageController extends HttpServlet {

    private ISetDAO setDAO = new SetDAO(DatabaseProperties.getInstance(), new PhotoDAO(DatabaseProperties.getInstance()));
    private ISetService setService = new SetService(setDAO);

    /**
     * shows index.jsp which shows all sets
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SetModel setModel;
            if (request.getParameter("user") == null) {
                setModel = new SetModel(setService, "Mastermindzh");
            } else {
                setModel = new SetModel(setService, request.getParameter("user"));
            }
            List<Set> sets = setModel.getSets();
            request.setAttribute("items", sets);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "An error has occured.. " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
