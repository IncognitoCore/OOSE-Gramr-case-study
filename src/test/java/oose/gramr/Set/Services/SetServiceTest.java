package oose.gramr.Set.Services;

import oose.gramr.Photo.Domain.Photo;
import oose.gramr.Set.DataSource.ISetDAO;
import oose.gramr.Set.DataSource.SetDAO;
import oose.gramr.Set.Domain.Set;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class SetServiceTest {

    private ISetService setService;
    private ISetDAO setDAO;

    @org.junit.Before
    public void setUp() throws Exception {
        setDAO = mock(ISetDAO.class);
        setService = new SetService(setDAO);
    }

    @Test
    public void findAll() throws Exception {
        List<Set> sets = new ArrayList<>();
        Set s = mock(Set.class);
        sets.add(s);
        when(setDAO.findAll("test")).thenReturn(sets);

        setService.findAll("test");

        verify(setDAO, times(1)).findAll("test");
    }

    @Test
    public void findByName() throws Exception {
        List<Set> sets = new ArrayList<>();
        Set s = mock(Set.class);
        sets.add(s);
        when(setDAO.findByName("test", "test")).thenReturn(sets);

        setService.findByName("test", "test");

        verify(setDAO, times(1)).findByName("test", "test");
    }

    @Test
    public void findPhotosInSetByID() throws Exception {
        List<Photo> photos = new ArrayList<>();
        Photo photo = mock(Photo.class);
        photos.add(photo);
        when(setDAO.findPhotosInSetByID(1)).thenReturn(photos);

        setService.findPhotosInSetByID(1);

        verify(setDAO, times(1)).findPhotosInSetByID(1);
    }

    @Test
    public void addSet() throws Exception {
        List<Integer> photos = new ArrayList<>();
        List<Integer> privacies = new ArrayList<>();
        photos.add(1);
        privacies.add(1);

        setService.addSet("test", "test", photos, privacies);

        verify(setDAO, times(1)).addSet("test", "test", photos, privacies);
    }

    @Test
    public void addPhotoToSet() throws Exception {
        setService.addPhotoToSet(1, 1, true);

        verify(setDAO, times(1)).addPhotoToSet(1, 1, true);
    }

    @Test
    public void saveSet() throws Exception {
        setService.saveSet(1, "test");

        verify(setDAO, times(1)).saveSet(1, "test");
    }
}