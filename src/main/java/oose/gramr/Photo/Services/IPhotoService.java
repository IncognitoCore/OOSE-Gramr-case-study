package oose.gramr.Photo.Services;

import oose.gramr.Exceptions.PhotoException;
import oose.gramr.Photo.Domain.Photo;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for PhotoService ( which manages the PhotoDAO and the PhotoModel)
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
public interface IPhotoService {
    List<Photo> getAllPhotos(String user) throws SQLException;

    List<Photo> findByTitle(String title, String user) throws SQLException;

    void applyFilter(int photoId, String filterAsString) throws SQLException, PhotoException;

    void savePhoto(Photo photo) throws SQLException;

    void addPhoto(Photo photo) throws SQLException;
}
