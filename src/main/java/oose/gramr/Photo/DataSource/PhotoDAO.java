package oose.gramr.Photo.DataSource;

import oose.gramr.DatabaseConnection.DatabaseProperties;
import oose.gramr.Factory.FilterFactory;
import oose.gramr.Photo.Domain.Photo;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Data access object for the Photo object.
 * <p/>
 * This class covers the following requirements:
 * <p/>
 * - De applicatie moet naast MySQL5.1 ook minimaal 1 andere relationele database kunnen ondersteunen.
 * - Bij het wisselen van database moet de code niet opnieuw gecompileerd hoeven te worden.
 * - De applicatie moet eenvoudig kunnen wisselen van een relationele (RDBMS) opslag naar een andere opslag zoals NoSQL of flat files.
 * - De applicatie maakt gebruik van de volgende APIs en frameworks:
 * JSP
 * Servlet v3.0
 * JAX-RS v2.0 (REST, JSON)
 * Jersey v2.17 (REST)
 * Guice v4.0 (Dependency Injection)
 * JDBC
 * JDBC driver v5.1.34 voor MySQL
 * - De communicatielaag en databasetoegang moeten los van elkaar testbaar zijn.
 */
public class PhotoDAO implements IPhotoDAO {
    private Logger logger = Logger.getLogger(getClass().getName());
    private DatabaseProperties databaseProperties;

    @Inject
    public PhotoDAO(DatabaseProperties databaseProperties) {
        setDatabaseProperties(databaseProperties);
        loadDbDriver(getDatabaseProperties());
    }

    /**
     * loads the db driver for this specific class.
     *
     * @param databaseProperties
     */
    private void loadDbDriver(DatabaseProperties databaseProperties) {
        try {
            Class.forName(databaseProperties.getDriver());
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Problem with driver: " + databaseProperties.getDriver(), e);
        }
    }

    /**
     * Find photo's with a given title and creator.
     *
     * @return List of photo's
     */
    public List<Photo> findByTitle(String title, String user) throws SQLException {
        ArrayList<Photo> photos = new ArrayList<Photo>();
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT PhotoId, title, creator, url, Filter_Naam, Description, cssClass FROM photo p LEFT JOIN filter f ON p.Filter_Naam = f.naam WHERE title LIKE ? AND creator = ? ORDER BY PhotoId");
            statement.setString(1, title + "%");
            statement.setString(2, user);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Photo photo = new Photo(result.getString("title"), result.getString("creator"), result.getString("description"), result.getString("url"));
                if (result.getString("Filter_Naam") != null) {
                    photo.setFilter(FilterFactory.getInstance().getFilter(result.getString("Filter_Naam")));
                }
                photo.setId(result.getInt("PhotoId"));
                photos.add(photo);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logAndThrow(e);
        }
        return photos;
    }

    /**
     * get all photos for a given user
     *
     * @param user user to find photo's for
     * @return list of photo's
     * @throws SQLException
     */
    public List<Photo> findAll(String user) throws SQLException {
        return findByTitle("", user);
    }

    /**
     * Find photo with given Id
     *
     * @return Photo
     */
    public Photo findById(int Id) throws SQLException {
        Photo photo = null;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT PhotoId, title, creator, url, Filter_Naam, Description, cssClass FROM photo p LEFT JOIN filter f ON p.Filter_Naam = f.naam WHERE PhotoId = ? ORDER BY PhotoId");
            statement.setInt(1, Id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                photo = new Photo(result.getString("title"), result.getString("creator"), result.getString("description"), result.getString("url"));
                if (result.getString("Filter_Naam") != null) {
                    photo.setFilter(FilterFactory.getInstance().getFilter(result.getString("Filter_Naam")));
                }
                photo.setId(result.getInt("PhotoId"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logAndThrow(e);
        }
        return photo;
    }

    /**
     * method to update photo properties
     *
     * @param photo photo object with updates
     * @throws SQLException
     */
    public void savePhoto(Photo photo) throws SQLException {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE photo SET title = ?, creator = ?, url = ?, Filter_Naam = ?, Description = ? WHERE PhotoId = ?");
            statement.setString(1, photo.getTitle());
            statement.setString(2, photo.getCreator());
            statement.setString(3, photo.getUrl());
            if (photo.getFilter() != null) {
                statement.setString(4, photo.getFilter().getName());
            } else {
                statement.setString(4, null);
            }
            if (photo.getDescription() != null) {
                statement.setString(5, photo.getDescription());
            } else {
                statement.setString(5, null);
            }
            statement.setInt(6, photo.getId());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logAndThrow(e);
        }
    }

    /**
     * method to add a photo to the DB
     *
     * @param photo photo to be added
     * @throws SQLException
     */
    public void addPhoto(Photo photo) throws SQLException {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO photo (title, creator, url, Filter_Naam, Description) VALUES (?,?,?,?,?)");
            statement.setString(1, photo.getTitle());
            statement.setString(2, photo.getCreator());
            statement.setString(3, photo.getUrl());
            if (photo.getFilter() != null) {
                statement.setString(4, photo.getFilter().getName());
            } else {
                statement.setString(4, null);
            }
            if (photo.getDescription() != null) {
                statement.setString(5, photo.getDescription());
            } else {
                statement.setString(4, null);
            }
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logAndThrow(e);
        }
    }

    /**
     * method to log and throw SQL exceptions
     *
     * @param e exception to log and throw
     * @throws SQLException
     */
    private void logAndThrow(SQLException e) throws SQLException {
        logger.log(Level.SEVERE, e.getSQLState());
        logger.log(Level.SEVERE, e.getMessage());
        logger.log(Level.SEVERE, "Error with database: " + getDatabaseProperties().getConnectionString(), e);
        throw new SQLException(e.getMessage());
    }

    private DatabaseProperties getDatabaseProperties() {
        return databaseProperties;
    }

    private void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getDatabaseProperties().getConnectionString(), getDatabaseProperties().getUser(), getDatabaseProperties().getPassword());
    }
}
