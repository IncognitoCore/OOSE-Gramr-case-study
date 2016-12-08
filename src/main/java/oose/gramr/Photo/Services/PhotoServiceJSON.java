package oose.gramr.Photo.Services;

import oose.gramr.Exceptions.PhotoException;
import oose.gramr.Photo.Domain.Photo;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Service which manages the PhotoDAO and the PhotoModel using JSON
 * <p/>
 * This class covers the following requirements:
 * - De front-end en back-end van de applicatie moeten elk in een eigen container kunnen draaien zodat ze via een protocol met elkaar kunnen communiceren.
 * - De applicatie maakt gebruik van de volgende APIs en frameworks:
 * <p/>
 * JSP
 * Servlet v3.0
 * JAX-RS v2.0 (REST, JSON)
 * Jersey v2.17 (REST)
 * Guice v4.0 (Dependency Injection)
 * JDBC
 * JDBC driver v5.1.34 voor MySQL
 * - De communicatielaag en databasetoegang moeten los van elkaar testbaar zijn.
 */
@Path("/photos")
@Singleton
public class PhotoServiceJSON implements IPhotoService {

    @Inject
    private PhotoService photoService;

    /**
     * returns all photos in JSON format given a user
     *
     * @param user
     * @return
     * @throws SQLException
     */
    @GET
    @Path("/{user}")
    @Produces("application/json")
    public List<Photo> getAllPhotos(@PathParam("user") final String user) {
        try {
            return photoService.getAllPhotos(user);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * returns all photos in JSON format given a search term
     *
     * @param title
     * @param user
     * @return
     * @throws SQLException
     */
    @GET
    @Path("/{user}/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Photo> findByTitle(@PathParam("user") final String user, @PathParam("title") final String title) {
        try {
            return photoService.findByTitle(title, user);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * applies a filter to a photo
     *
     * @param photoId
     * @param filterAsString
     * @throws SQLException
     * @throws PhotoException
     */
    public void applyFilter(final int photoId, final String filterAsString) throws SQLException, PhotoException {
        photoService.applyFilter(photoId, filterAsString);
    }

    /**
     * updates a photo given a photo
     *
     * @param photo
     * @throws SQLException
     */
    public void savePhoto(final Photo photo) throws SQLException {
        photoService.savePhoto(photo);
    }

    /**
     * adds a photo given a photo
     *
     * @param photo
     * @throws SQLException
     */
    public void addPhoto(final Photo photo) throws SQLException {
        photoService.addPhoto(photo);
    }

    /**
     * Apply a filter using JSON
     *
     * @param str
     * @return 201
     * @throws SQLException
     * @throws PhotoException
     */
    @POST
    @Path("/applyFilter")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response applyFilter(String str) throws SQLException, PhotoException {
        try {
            JSONObject inputJSON = new JSONObject(str);
            int photoID = inputJSON.getInt("photoID");
            String filterName = inputJSON.getString("filterName");
            photoService.applyFilter(photoID, filterName);
            String result = filterName + " applied to " + photoID;
            return Response.status(201).entity(result).build();
        } catch (Exception e) {
            if (e instanceof SQLException) {
                throw new SQLException(e);
            } else {
                return Response.status(501).entity("Couldn't apply filter").build();
            }
        }
    }

    /**
     * update a pgoto using JSON
     *
     * @param photo
     * @return 201
     * @throws SQLException
     */
    @POST
    @Path("/savephoto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savephoto(Photo photo) throws SQLException {
        try {
            photoService.savePhoto(photo);
            String result = "Photo: " + photo.getTitle() + " with owner:" + photo.getCreator();
            return Response.status(201).entity(result).build();
        } catch (Exception e) {
            if (e instanceof SQLException) {
                throw new SQLException(e);
            } else {
                return Response.status(501).entity("Couldn't update photo").build();
            }
        }
    }

    /**
     * add a photo using json
     *
     * @param photo
     * @return 201
     * @throws SQLException
     */
    @POST
    @Path("/addphoto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPhotoJSON(Photo photo) throws SQLException {
        try {
            photoService.addPhoto(photo);
            String result = "Photo: " + photo.getTitle() + " with owner:" + photo.getCreator();
            return Response.status(201).entity(result).build();
        } catch (Exception e) {
            if (e instanceof SQLException) {
                throw new SQLException(e);
            } else {
                return Response.status(501).entity("Couldn't add photo").build();
            }
        }
    }

}
