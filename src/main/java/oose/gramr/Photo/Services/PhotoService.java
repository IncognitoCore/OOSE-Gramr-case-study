package oose.gramr.Photo.Services;

import oose.gramr.Exceptions.PhotoException;
import oose.gramr.Photo.DataSource.IFilterDAO;
import oose.gramr.Photo.DataSource.IPhotoDAO;
import oose.gramr.Photo.Domain.Filter;
import oose.gramr.Photo.Domain.Photo;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

/**
 * Service which manages the PhotoDAO and the PhotoModel
 * <p/>
 * This class covers the following requirements:
 * - De front-end en back-end van de applicatie moeten samen in 1 container kunnen draaien zodat ze lokaal met elkaar kunnen communiceren.
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
public class PhotoService implements IPhotoService {
    private IPhotoDAO photoDAO;
    private IFilterDAO filterDAO;

    @Inject
    public PhotoService(IPhotoDAO photoDAO, IFilterDAO filterDAO) {
        this.photoDAO = photoDAO;
        this.filterDAO = filterDAO;
    }

    /**
     * returns all photos given a user
     *
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public List<Photo> getAllPhotos(String user) throws SQLException {
        return photoDAO.findAll(user);
    }

    /**
     * returns all photos given a search term
     *
     * @param title
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public List<Photo> findByTitle(String title, String user) throws SQLException {
        return photoDAO.findByTitle(title, user);
    }

    /**
     * applies a filter to a photo
     *
     * @param photoId
     * @param filterAsString
     * @throws SQLException
     * @throws PhotoException
     */
    @Override
    public void applyFilter(int photoId, String filterAsString) throws SQLException, PhotoException {
        Photo result = photoDAO.findById(photoId);
        if (result != null) {
            if (!filterAsString.equals("null")) {
                Filter filter = filterDAO.findFilter(filterAsString);
                result.setFilter(filter);
            } else {
                result.setFilter(null);
            }
            photoDAO.savePhoto(result);
        }
    }

    /**
     * updates a photo given a photo
     *
     * @param photo
     * @throws SQLException
     */
    @Override
    public void savePhoto(Photo photo) throws SQLException {
        photoDAO.savePhoto(photo);
    }

    /**
     * adds a photo given a photo
     *
     * @param photo
     * @throws SQLException
     */
    @Override
    public void addPhoto(Photo photo) throws SQLException {
        photoDAO.addPhoto(photo);
    }

}
