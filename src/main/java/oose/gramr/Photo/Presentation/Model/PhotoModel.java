package oose.gramr.Photo.Presentation.Model;

import oose.gramr.Photo.Domain.Photo;
import oose.gramr.Photo.Services.IPhotoService;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Model which hold multiple photos for the view
 *
 * This class covers the following requirements:
 * Views (JSP-bestanden) bevatten geen Java-code, alleen JSP of JSTL markup.
 */
public class PhotoModel {

    private List<Photo> photos;

    public PhotoModel(IPhotoService photoService, String user) throws SQLException {
        setPhotos(photoService.getAllPhotos(user));
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Photo> getAllPhotos(){
        return this.photos;
    }
}
