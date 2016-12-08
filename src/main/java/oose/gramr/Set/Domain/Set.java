package oose.gramr.Set.Domain;

import oose.gramr.Photo.Domain.Photo;

import java.util.ArrayList;
import java.util.List;
/**
 * POJO for a Set
 *
 *
 * This class covers the following requirements:
 * -
 * - Foto kan in een set prive zijn.
 * - Gebruiker kan een foto toevoegen.
 * - De gebruiker kan de naam van een set aanpassen
 */
public class Set {

    private String owner;
    private String name;
    private int id;
    private List<Photo> photos = new ArrayList<>();

    public Set(){

    }

    public Set(String owner, String name, int id) {
        this. owner = owner;
        changeName(name);
        setId(id);
    }

    public Set(String owner, String name, int id, List<Photo> photos) {
        this.owner = owner;
        changeName(name);
        setId(id);
        setPhotos(photos);
    }

    public String toString(){
        String strToReturn = "Set{" +
                "id=" + getId() +
                ", name='" + getName() + "\'" +
                ", owner='" + getOwner() + "\'" +
        ", Photos={";
        for(Photo photo:getPhotos()){
            strToReturn += photo.toString();
        }
        strToReturn += "}}";

        return strToReturn;
    }

    /**
     * Add a photo to the set
     * @param p
     */
    public void addPhoto(Photo p){
        getPhotos().add(p);
    }

    /**
     * remove a photo from the set
     * @param p
     */
    public void removePhoto(Photo p){
        getPhotos().remove(p);
    }

    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
