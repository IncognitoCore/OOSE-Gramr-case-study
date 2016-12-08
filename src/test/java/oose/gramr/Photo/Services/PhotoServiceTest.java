package oose.gramr.Photo.Services;

import oose.gramr.Photo.DataSource.FilterDAO;
import oose.gramr.Photo.DataSource.IFilterDAO;
import oose.gramr.Photo.DataSource.IPhotoDAO;
import oose.gramr.Photo.DataSource.PhotoDAO;
import oose.gramr.Photo.Domain.Filter;
import oose.gramr.Photo.Domain.Photo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PhotoServiceTest {

    private IPhotoService ps;
    private IPhotoDAO pd;
    private IFilterDAO fd;
    @org.junit.Before
    public void setUp() throws Exception {
        pd = mock(IPhotoDAO.class);
        fd = mock(IFilterDAO.class);
        ps=new PhotoService(pd,fd);
    }

    @org.junit.Test
    public void getAllPhotos() throws Exception {
        List<Photo> photos = new ArrayList<>();
        Photo p = mock(Photo.class);
        photos.add(p);
        when(pd.findAll("test")).thenReturn(photos);

        ps.getAllPhotos("test");

        verify(pd, times(1)).findAll("test");
    }

    @org.junit.Test
    public void findByTitle() throws Exception {
        List<Photo> photos = new ArrayList<>();
        Photo p = mock(Photo.class);
        photos.add(p);
        when(pd.findByTitle("test","test")).thenReturn(photos);

        ps.findByTitle("test", "test");

        verify(pd, times(1)).findByTitle("test", "test");
    }

    @org.junit.Test
    public void applyFilter() throws Exception {
        Photo p = mock(Photo.class);
        Filter f = mock(Filter.class);
        when(fd.findFilter("grey")).thenReturn(f);
        when(pd.findById(1)).thenReturn(p);

        ps.applyFilter(1, "grey");

        verify(fd, times(1)).findFilter("grey");
        verify(pd,times(1)).findById(1);
        verify(p, times(1)).setFilter(f);
        verify(pd,times(1)).savePhoto(p);
    }

    @org.junit.Test
    public void savePhoto() throws Exception {
        Photo p = mock(Photo.class);

        ps.savePhoto(p);

        verify(pd, times(1)).savePhoto(p);
    }

    @org.junit.Test
    public void addPhoto() throws Exception {
        Photo p = mock(Photo.class);

        ps.addPhoto(p);

        verify(pd, times(1)).addPhoto(p);
    }
}