package oose.gramr.Photo.Domain;


/**
 * Specialisation of the Filter object.
 * Used for displaying pictures with a vintage effect
 * <p/>
 * This requirement covers the following requirements:
 * Een foto kan 0 tot 1  filter hebben.
 */
public class VintageFilter extends Filter {

    private int upperLeftX = 0;
    private int upperLeftY = 0;
    private int lowerRightX = 0;
    private int lowerRightY = 0;

    public VintageFilter(String naam, String cssClass) {
        super(naam, cssClass);
        setDescription("Vintage filter");
    }

    public int getUpperLeftX() {
        return upperLeftX;
    }

    public void setUpperLeftX(int upperLeftX) {
        this.upperLeftX = upperLeftX;
    }

    public int getUpperLeftY() {
        return upperLeftY;
    }

    public void setUpperLeftY(int upperLeftY) {
        this.upperLeftY = upperLeftY;
    }

    public int getLowerRightX() {
        return lowerRightX;
    }

    public void setLowerRightX(int lowerRightX) {
        this.lowerRightX = lowerRightX;
    }

    public int getLowerRightY() {
        return lowerRightY;
    }

    public void setLowerRightY(int lowerRightY) {
        this.lowerRightY = lowerRightY;
    }

    public String toString() {
        return "class=" + getCssClass() + "Description=" + getDescription() + "values=" + getUpperLeftX() + getLowerRightX() + getLowerRightY() + getUpperLeftY();
    }

}
