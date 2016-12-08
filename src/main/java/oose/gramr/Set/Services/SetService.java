package oose.gramr.Set.Services;

import com.google.inject.Inject;
import oose.gramr.Photo.Domain.Photo;
import oose.gramr.Set.DataSource.ISetDAO;
import oose.gramr.Set.DataSource.SetDAO;
import oose.gramr.Set.Domain.Set;

import java.sql.SQLException;
import java.util.List;

/**
 * Service which manages the SetDAO and the PhotoModel
 *
 * This class covers the following requirements:
 * - De front-end en back-end van de applicatie moeten samen in 1 container kunnen draaien zodat ze lokaal met elkaar kunnen communiceren.
 * - De front-end en back-end van de applicatie moeten elk in een eigen container kunnen draaien zodat ze via een protocol met elkaar kunnen communiceren.
 * - De applicatie maakt gebruik van de volgende APIs en frameworks:

     JSP
     Servlet v3.0
     JAX-RS v2.0 (REST, JSON)
     Jersey v2.17 (REST)
     Guice v4.0 (Dependency Injection)
     JDBC
     JDBC driver v5.1.34 voor MySQL
 * - De communicatielaag en databasetoegang moeten los van elkaar testbaar zijn.
 * - De gebruiker kan een foto aan een set toevoegen
 * - De gebruiker kan de naam van een set aanpassen
 * - De gebruiker kan een set toevoegen.
 */
public class SetService implements ISetService{
    private ISetDAO setDAO;

    @Inject
    public SetService(ISetDAO setDAO) {
        setSetDAO(setDAO);
    }

    /**
     * returns all sets given a user
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public List<Set> findAll(String user) throws SQLException {
        return setDAO.findAll(user);
    }

    /**
     * returns all sets given a user and a search param
     * @param name
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public List<Set> findByName(String name, String user) throws SQLException {
        return setDAO.findByName(name,user);
    }

    /**
     * returns all photos in a set using an id.
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Photo> findPhotosInSetByID(int id) throws SQLException {
        return setDAO.findPhotosInSetByID(id);
    }

    /**
     * Add a set given user input
     * @param name
     * @param user
     * @param photos
     * @param privates
     * @throws SQLException
     */
    @Override
    public void addSet(String name, String user, List<Integer> photos, List<Integer> privates) throws SQLException {
        setDAO.addSet(name,user,photos,privates);
    }

    /**
     * adds a photo to a set given a setID and a photoID + is private
     * @param setID
     * @param photoID
     * @param isPrivate
     * @throws SQLException
     */
    @Override
    public void addPhotoToSet(int setID, int photoID, boolean isPrivate) throws SQLException {
        setDAO.addPhotoToSet(setID,photoID,isPrivate);
    }

    /**
     * updates a set's name given an id and a new name
     * @param setId
     * @param naam
     * @throws SQLException
     */
    public void saveSet(int setId, String naam) throws SQLException{
        setDAO.saveSet(setId, naam);
    }

    private void setSetDAO(ISetDAO setDAO) {
        this.setDAO = setDAO;
    }
}
