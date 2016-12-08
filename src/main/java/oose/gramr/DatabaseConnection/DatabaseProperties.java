package oose.gramr.DatabaseConnection;

import com.google.inject.Inject;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The DatabaseProperties class is responsible for reading the database.properties file.
 * This class covers the following requirements:
 * <p/>
 * - De applicatie moet naast MySQL5.1 ook minimaal 1 andere relationele database kunnen ondersteunen.
 * - Bij het wisselen van database moet de code niet opnieuw gecompileerd hoeven te worden.
 * - De applicatie moet eenvoudig kunnen wisselen van een relationele (RDBMS) opslag naar een andere opslag zoals NoSQL of flat files.
 */
public class DatabaseProperties {

    static private DatabaseProperties dbProperties;
    private Properties property;
    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * method to return the same instance over and over again (singleton)
     *
     * @return instance of DatabaseProperties
     */
    public static DatabaseProperties getInstance() {
        if (dbProperties == null) {
            dbProperties = new DatabaseProperties();
        }
        return dbProperties;
    }

    @Inject
    private DatabaseProperties() {
        loadProperties();
    }

    /**
     * Load and map the database.properties values.
     */
    private void loadProperties() {
        this.property = new Properties();
        try {
            property.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can't access property file: database.properties", e);
        }
    }

    /**
     * Return the connectionstring in the propertiesfile
     *
     * @return connectionstring
     */
    public String getConnectionString() {
        return this.property.getProperty("connectionstring");
    }

    /**
     * Return the driver classname in the propertiesfile
     *
     * @return Driver class name
     */
    public String getDriver() {
        return this.property.getProperty("driver");
    }

    /**
     * returns user from properties file
     *
     * @return user
     */
    public String getUser() {
        return this.property.getProperty("user");
    }

    /**
     * returns password from properties file
     *
     * @return password
     */
    public String getPassword() {
        return this.property.getProperty("password");
    }

}
