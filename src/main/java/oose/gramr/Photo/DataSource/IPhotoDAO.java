package oose.gramr.Photo.DataSource;

import oose.gramr.Photo.Domain.Photo;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for the PhotoDAO object.
 *
 * This class covers the following requirements:
 *
 * - De applicatie moet naast MySQL5.1 ook minimaal 1 andere relationele database kunnen ondersteunen.
 * - Bij het wisselen van database moet de code niet opnieuw gecompileerd hoeven te worden.
 * - De applicatie moet eenvoudig kunnen wisselen van een relationele (RDBMS) opslag naar een andere opslag zoals NoSQL of flat files.
 * - De applicatie maakt gebruik van de volgende APIs en frameworks:
         JSP
         Servlet v3.0
         JAX-RS v2.0 (REST, JSON)
         Jersey v2.17 (REST)
         Guice v4.0 (Dependency Injection)
         JDBC
         JDBC driver v5.1.34 voor MySQL
 * - De communicatielaag en databasetoegang moeten los van elkaar testbaar zijn.
 */
public interface IPhotoDAO {
    List<Photo> findByTitle(String title, String user) throws SQLException;
    List<Photo> findAll(String user) throws SQLException;
    Photo findById(int id) throws SQLException;
    void savePhoto(Photo photo) throws SQLException;
    void addPhoto(Photo photo) throws SQLException;
}
