package oose.gramr.Photo.Domain;

public class GrayFilter extends Filter {
    private int percentage = 0;

    public GrayFilter(String naam, String cssClass) {
        super(naam, cssClass);
        setDescription("Grayfilter");
    }

    /**
     * Specialisation of the Filter object.
     * Used for displaying gray pictures
     * <p/>
     * This requirement covers the following requirements:
     * Een foto kan 0 tot 1  filter hebben.
     */
    public GrayFilter() {
        super("", "");
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String toString() {
        return "class=" + getCssClass() + "Description=" + getDescription() + "percentage=" + getPercentage();
    }
}
