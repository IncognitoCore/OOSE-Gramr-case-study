package oose.gramr.Photo.DataSource;

import oose.gramr.DatabaseConnection.DatabaseProperties;
import oose.gramr.Factory.FilterFactory;
import oose.gramr.Photo.Domain.Filter;

import javax.inject.Inject;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data access object for the Filter object.
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
public class FilterDAO implements IFilterDAO {
    private Logger logger = Logger.getLogger(getClass().getName());
    private DatabaseProperties databaseProperties;

    @Inject
    public FilterDAO(DatabaseProperties databaseProperties) {
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
     * Find Filter with a given name.
     *
     * @return Filter
     */
    public Filter findFilter(String name) throws SQLException {
        Filter filter = null;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT naam, cssClass FROM filter WHERE naam = ?");
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            result.next();
            filter = FilterFactory.getInstance().getFilter(result.getString("naam"));
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logAndThrow(e);
        }
        return filter;
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


