package oose.gramr.Photo.Domain;

public abstract class Filter {
    private String name;
    private String cssClass;
    private String description;

    /**
     * POJO for a filter
     * Abstract superclass for specific filters
     * <p/>
     * This class covers the following requirements:
     * - Een foto kan 0 tot 1  filter hebben.
     *
     * @param name     name of the filter
     * @param cssClass css class of the filter
     */
    public Filter(String name, String cssClass) {
        this.setName(name);
        this.setCssClass(cssClass);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "class=" + getCssClass() + "Description=" + getDescription();
    }
}
