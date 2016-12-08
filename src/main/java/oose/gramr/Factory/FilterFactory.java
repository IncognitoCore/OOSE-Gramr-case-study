package oose.gramr.Factory;

import oose.gramr.Photo.Domain.Filter;
import oose.gramr.Photo.Domain.GrayFilter;
import oose.gramr.Photo.Domain.InvertedFilter;
import oose.gramr.Photo.Domain.VintageFilter;

/**
 * Factory class to create filters
 */
public class FilterFactory {

    static private FilterFactory filterFactory;

    private FilterFactory(){}

    /**
     * method to return the same instance over and over again (singleton)
     * @return instance of FilterFactory
     */
    public static FilterFactory getInstance(){
        if(filterFactory == null){
            filterFactory = new FilterFactory();
        }
        return filterFactory;
    }

    /**
     * returns a filter given a name
     * @param name
     * @return
     */
    public Filter getFilter(String name){
        Filter filter;
        switch (name){
            case "Gray":
                filter = new GrayFilter("Gray", "gray");
                break;
            case "Invert":
                filter = new InvertedFilter("Invert", "invert");
                break;
            case "Vintage":
                filter = new VintageFilter("Vintage", "vintage");
                break;
            default: filter = null;
        }
        return filter;
    }
}
