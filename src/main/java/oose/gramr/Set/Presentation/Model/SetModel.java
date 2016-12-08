package oose.gramr.Set.Presentation.Model;

import oose.gramr.Set.Domain.Set;
import oose.gramr.Set.Services.ISetService;

import java.sql.SQLException;
import java.util.List;

/**
 * Model which hold multiple sets for the view
 *
 * This class covers the following requirements:
 * Views (JSP-bestanden) bevatten geen Java-code, alleen JSP of JSTL markup.
 */
public class SetModel {

    private List<Set> sets;

    public SetModel(ISetService setService, String user) throws SQLException {
        setSets(setService.findAll(user));
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }
}
