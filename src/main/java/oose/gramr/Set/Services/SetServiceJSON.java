package oose.gramr.Set.Services;


import oose.gramr.Photo.Domain.Photo;
import oose.gramr.Set.Domain.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Service which manages the SetDAO and the PhotoModel and returns JSON
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
 * - De gebruiker kan een foto aan een set toevoegen
 * - De gebruiker kan de naam van een set aanpassen
 * - De gebruiker kan een set toevoegen.
 */
@Path("/sets")
public class SetServiceJSON implements ISetService {

    @Inject
    private SetService setService;

    /**
     * returns all sets in JSON format given a user
     *
     * @param user
     * @return
     * @throws SQLException
     */
    @GET
    @Path("/{user}")
    @Produces("application/json")
    @Override
    public List<Set> findAll(@PathParam("user") final String user) throws SQLException {
        return setService.findAll(user);
    }

    /**
     * returns all sets in JSON format given a user and a search param
     *
     * @param name
     * @param user
     * @return
     * @throws SQLException
     */
    @GET
    @Path("/{user}/{name}")
    @Produces("application/json")
    @Override
    public List<Set> findByName(@PathParam("user") final String name, @PathParam("name") final String user) throws SQLException {
        return setService.findByName(user, name);
    }

    /**
     * returns all photos in a set (in JSON format) using an id.
     *
     * @param id
     * @return
     * @throws SQLException
     */
    @GET
    @Path("byid/{id}")
    @Produces("application/json")
    @Override
    public List<Photo> findPhotosInSetByID(@PathParam("id") final int id) throws SQLException {
        return setService.findPhotosInSetByID(id);
    }

    /**
     * Add a set given user input
     *
     * @param name
     * @param user
     * @param photos
     * @param privates
     * @throws SQLException
     */
    public void addSet(String name, String user, List<Integer> photos, List<Integer> privates) throws SQLException {
        setService.addSet(name, user, photos, privates);
    }

    /**
     * adds a photo to a set given a setID and a photoID + is private
     *
     * @param setID
     * @param photoID
     * @param isPrivate
     * @throws SQLException
     */
    public void addPhotoToSet(int setID, int photoID, boolean isPrivate) throws SQLException {
        setService.addPhotoToSet(setID, photoID, isPrivate);
    }

    /**
     * updates a set's name given an id and a new name
     *
     * @param setID
     * @param naam
     * @throws SQLException
     */
    @Override
    public void saveSet(int setID, String naam) throws SQLException {
        setService.saveSet(setID, naam);
    }


    /**
     * Add a set using JSON
     *
     * @param set
     * @return
     * @throws SQLException
     */
    @POST
    @Path("/addSetJSON")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSet(Set set) throws SQLException {
        try{
            List<Integer> privates = new ArrayList<>();
            List<Integer> photos = new ArrayList<>();
            for (Photo p : set.getPhotos()) {
                photos.add(p.getId());
                if (p.getIsPrivate() != null && p.getIsPrivate().isPrivacy()) {
                    privates.add(p.getId());
                }
            }

            setService.addSet(set.getName(), set.getOwner(), photos, privates);
            String result = "set saved: " + set.getName() + " with owner:" + set.getOwner();
            return Response.status(201).entity(result).build();
        }catch (Exception e){
            if(e instanceof SQLException){
                throw new SQLException(e);
            }else{
                return Response.status(501).entity("Couldn't add set").build();
            }
        }
    }

    /**
     * add a photo to a set using JSON
     *
     * @param str
     * @return
     * @throws SQLException
     */
    @POST
    @Path("/addPhotoToSetJSON")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPhotoToSet(String str) throws SQLException {
        try{
            JSONObject inputJSON = new JSONObject(str);
            int setID = inputJSON.getInt("setID");
            int photoID = inputJSON.getInt("photoID");
            setService.addPhotoToSet(setID, photoID, false);
            String result = "Photo : " + photoID + " added to set " + setID;
            return Response.status(201).entity(result).build();
        }catch (Exception e){
            if(e instanceof SQLException){
                throw new SQLException(e);
            }else{
                return Response.status(501).entity("Couldn't add photo to set").build();
            }
        }
    }

    /**
     * changes a setname with JSON
     *
     * @param str
     * @return
     * @throws SQLException
     */
    @POST
    @Path("/changeName")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeName(String str) throws SQLException {
        try{
            JSONObject inputJSON = new JSONObject(str);
            int setID = inputJSON.getInt("setID");
            String newName = inputJSON.getString("setName");
            setService.saveSet(setID, newName);
            String result = "Set " + setID + " renamed to: " + newName;
            return Response.status(201).entity(result).build();
        }catch (Exception e){
            if(e instanceof SQLException){
                throw new SQLException(e);
            }else{
                return Response.status(501).entity("Couldn't change the name of set.").build();
            }
        }
    }

}
