package oose.gramr.Set.Services;

import oose.gramr.Photo.Domain.Photo;
import oose.gramr.Set.Domain.Set;

import java.sql.SQLException;
import java.util.List;

public interface ISetService {
    List<Set> findAll(String user) throws SQLException;
    List<Set> findByName(String name, String user) throws SQLException;
    List<Photo> findPhotosInSetByID(int id) throws SQLException;
    void addSet(String name, String user,List<Integer> photos,List<Integer> privates) throws SQLException;
    void addPhotoToSet(int setID, int photoID, boolean isPrivate) throws SQLException;
    void saveSet(int setID, String naam) throws SQLException;
}
