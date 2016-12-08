package oose.gramr.Guice;

import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;
import oose.gramr.Photo.DataSource.FilterDAO;
import oose.gramr.Photo.DataSource.IFilterDAO;
import oose.gramr.Photo.DataSource.IPhotoDAO;
import oose.gramr.Photo.DataSource.PhotoDAO;
import oose.gramr.Photo.Presentation.Controller.AddPhotoPageController;
import oose.gramr.Photo.Presentation.Controller.ApplyFilterPageController;
import oose.gramr.Photo.Presentation.Controller.PhotoViewPageController;
import oose.gramr.Photo.Services.IPhotoService;
import oose.gramr.Photo.Services.PhotoService;
import oose.gramr.Set.DataSource.ISetDAO;
import oose.gramr.Set.DataSource.SetDAO;
import oose.gramr.Set.Presentation.Controller.AddSetController;
import oose.gramr.Set.Presentation.Controller.EditSetPageController;
import oose.gramr.Set.Presentation.Controller.SetAddPhotoController;
import oose.gramr.Set.Presentation.Controller.SetViewPageController;
import oose.gramr.Set.Services.ISetService;
import oose.gramr.Set.Services.SetService;

/**
 * Makes sure guice knows which class to inject into the injection points.
 */
public class AppBinding extends ServletModule {

    @Override
    protected void configureServlets() {
        super.configureServlets();

        /* @singleton didn't work so.... something with guice and multiple instances*/
        bind(ApplyFilterPageController.class).in(Scopes.SINGLETON);
        bind(PhotoViewPageController.class).in(Scopes.SINGLETON);
        bind(SetAddPhotoController.class).in(Scopes.SINGLETON);
        bind(AddPhotoPageController.class).in(Scopes.SINGLETON);
        bind(AddSetController.class).in(Scopes.SINGLETON);
        bind(EditSetPageController.class).in(Scopes.SINGLETON);
        bind(SetViewPageController.class).in(Scopes.SINGLETON);

        /* bind urls */
        serve("/addphototoset").with(SetAddPhotoController.class);
        serve("/applyfilter").with(ApplyFilterPageController.class);
        serve("/photos").with(PhotoViewPageController.class);
        serve("/addphoto").with(AddPhotoPageController.class);
        serve("/addset").with(AddSetController.class);
        serve("/editset").with(EditSetPageController.class);
        serve("/").with(SetViewPageController.class);

        bind(IFilterDAO.class).to(FilterDAO.class);
        bind(IPhotoDAO.class).to(PhotoDAO.class);
        bind(IPhotoService.class).to(PhotoService.class);

        bind(ISetService.class).to(SetService.class);
        bind(ISetDAO.class).to(SetDAO.class);

    }
}
