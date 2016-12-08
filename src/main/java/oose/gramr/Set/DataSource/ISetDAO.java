package oose.gramr.Set.DataSource;

import oose.gramr.Photo.DataSource.IPhotoDAO;
import oose.gramr.Photo.Domain.Photo;
import oose.gramr.Set.Domain.Set;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for the SetDAO object
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
public interface ISetDAO {
    List<Set> findAll(String user) throws SQLException;
    List<Set> findByName(String name, String user) throws SQLException;
    List<Photo> findPhotosInSetByID(int id) throws SQLException;
    void addSet(String name, String user,List<Integer> photos,List<Integer> privates) throws SQLException;
    void addPhotoToSet(int setID, int photoID, boolean isPrivate) throws SQLException;
    void saveSet(int setID, String name) throws SQLException;
}
