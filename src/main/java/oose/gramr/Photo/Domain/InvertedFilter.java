package oose.gramr.Photo.Domain;

/**
 * Specialisation of the Filter object.
 * Used for displaying inverted pictures
 * <p/>
 * This requirement covers the following requirements:
 * Een foto kan 0 tot 1  filter hebben.
 */
public class InvertedFilter extends Filter {
    public InvertedFilter(String naam, String cssClass) {
        super(naam, cssClass);
        setDescription("Inverted filter");
    }

}
