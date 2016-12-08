package oose.gramr.Photo.Domain;

/**
 * POJO for a Photo
 * <p/>
 * <p/>
 * This class covers the following requirements:
 * - Een foto kan 0 tot 1  filter hebben.
 * - Foto kan in een set prive zijn.
 * - Gebruiker kan een foto toevoegen.
 */

import oose.gramr.Set.Domain.Privacy;

public class Photo {
    private int id;
    private String title;
    private String creator;
    private String url;
    private Filter filter;
    private String description;
    private Privacy isPrivate;

    public Photo(String title, String creator, String description, String url) {
        this.setTitle(title);
        this.setDescription(description);
        this.setCreator(creator);
        this.setUrl(url);
        this.isPrivate = new Privacy();
    }

    public Photo() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", creator='" + getCreator() + '\'' +
                ", url='" + getUrl() + '\'' +
                ", filter=" + getFilter() +
                ", description='" + getDescription() + '\'' +
                ", isPrivacy='" + getIsPrivate() + '\'' +
                '}';
    }

    public void setIsPrivate(Privacy isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Privacy getIsPrivate() {
        return isPrivate;
    }

    public String getTitle() {
        return title;
    }

    public String getCreator() {
        return creator;
    }

    public String getUrl() {
        return url;
    }

    public Filter getFilter() {
        return filter;
    }

    public String getDescription() {
        return description;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
