package oose.gramr.Set.DataSource;

import com.google.inject.Inject;
import oose.gramr.DatabaseConnection.DatabaseProperties;
import oose.gramr.Photo.DataSource.IPhotoDAO;
import oose.gramr.Photo.Domain.Photo;
import oose.gramr.Set.Domain.Privacy;
import oose.gramr.Set.Domain.Set;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data access object for the SET object.
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
public class SetDAO implements ISetDAO{
    private Logger logger = Logger.getLogger(getClass().getName());
    private DatabaseProperties databaseProperties;
    private IPhotoDAO dao;

    @Inject
    public SetDAO(DatabaseProperties databaseProperties, IPhotoDAO dao) {
        setDatabaseProperties(databaseProperties);
        loadDbDriver(getDatabaseProperties());
        setDao(dao);
    }

    /**
     * loads the db driver for this specific class.
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
     * Returns a list of sets given a user
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public List<Set> findAll(String user) throws SQLException {
        return findByName("",user);
    }

    /**
     * returns a list of sets given a search param and a user
     * @param name
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public List<Set> findByName(String name, String user) throws SQLException {
        ArrayList<Set> sets = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT SetID, Naam, User_Username from `set` WHERE Naam like ? AND User_Username = ?");
            statement.setString(1, name + "%");
            statement.setString(2, user);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                sets.add(new Set(result.getString("User_Username"),result.getString("Naam"),result.getInt("SetID"),findPhotosInSetByID(result.getInt("SetID"))));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logAndThrow(e);
        }
        return sets;
    }

    /**
     * returns a list of photos in a set given the setID
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Photo> findPhotosInSetByID(int id) throws SQLException {
        ArrayList<Photo> photos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT Photo_PhotoID, isPrivate from photo_belongs_to_set where Set_SetID = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Photo photo = getDao().findById(result.getInt("Photo_PhotoID"));
                if(result.getBoolean("isPrivate")){
                    photo.getIsPrivate().togglePrivate();
                }
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
     * adds a set to the database
     * @param name
     * @param user
     * @param photos
     * @param privates
     * @throws SQLException
     */
    @Override
    public void addSet(String name, String user, List<Integer> photos,List<Integer> privates) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("INSERT INTO `set` (Naam, User_Username) VALUES (?,?)");
            statement.setString(1,name);
            statement.setString(2,user);
            statement.executeUpdate();
            int setId = getSetIdWithUsernameAndName(user,name);
            for(Integer i:photos){
                boolean isPrivate = false;
                if(privates.contains(i)){
                    isPrivate = true;
                }
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                statement = connection.prepareStatement("INSERT INTO photo_belongs_to_set (Photo_photoID, Set_SetID, isPrivate) VALUES (?,?,?)");
                statement.setInt(1,i);
                statement.setInt(2,setId);
                statement.setBoolean(3,isPrivate);
                statement.executeUpdate();
                statement.close();
            }
        }catch (SQLException e){
            connection.rollback();
            logAndThrow(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
            }
            if(!connection.isClosed()){
                connection.close();
            }
        }
    }

    /**
     * find a set based on a user and a name
     * @param user
     * @param name
     * @return
     * @throws SQLException
     */
    private int getSetIdWithUsernameAndName(String user, String name) throws SQLException{
        try {
            Connection connection = getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            PreparedStatement statement = connection.prepareStatement("select SetID from  `set` where User_Username = ? AND Naam = ? ");
            statement.setString(1,user);
            statement.setString(2,name);
            int setID = 0;
            ResultSet result = statement.executeQuery();
            while(result.next()){
                setID = result.getInt("SetID");
            }
            statement.close();
            connection.close();
            return setID;
        } catch (SQLException e) {
            logAndThrow(e);
        }
        return 0;
    }

    /**
     * add a photo to a set
     * @param setID
     * @param photoID
     * @param isPrivate
     * @throws SQLException
     */
    @Override
    public void addPhotoToSet(int setID, int photoID, boolean isPrivate) throws SQLException {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO photo_belongs_to_set (Photo_photoID, Set_SetID, isPrivate) VALUES (?,?,?)");
            statement.setInt(1,photoID);
            statement.setInt(2,setID);
            statement.setBoolean(3,isPrivate);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logAndThrow(e);
        }
    }

    /**
     * updates a set given it's ID and a new name
     * @param setId
     * @param name
     * @throws SQLException
     */
    public void saveSet(int setId, String name) throws SQLException{
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE `set` SET naam = (?) WHERE SetID = ?");
            statement.setString(1,name);
            statement.setInt(2,setId);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logAndThrow(e);
        }
    }
    /**
     * method to log and throw SQL exceptions
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

    private IPhotoDAO getDao() {
        return dao;
    }

    private void setDao(IPhotoDAO dao) {
        this.dao = dao;
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(getDatabaseProperties().getConnectionString(), getDatabaseProperties().getUser(), getDatabaseProperties().getPassword());
    }

}
