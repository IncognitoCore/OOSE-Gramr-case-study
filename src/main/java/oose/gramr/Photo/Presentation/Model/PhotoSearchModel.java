package oose.gramr.Photo.Presentation.Model;

import oose.gramr.Photo.Domain.Photo;
import oose.gramr.Photo.Services.IPhotoService;

import java.sql.SQLException;
import java.util.List;

public class PhotoSearchModel {
    private List<Photo> photos;

    public PhotoSearchModel(IPhotoService photoService, String user, String titel) throws SQLException {
        setPhotos(photoService.findByTitle(titel, user));
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Photo> getAllPhotos(){
        return this.photos;
    }
}
