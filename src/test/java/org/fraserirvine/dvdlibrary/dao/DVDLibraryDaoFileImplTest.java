package org.fraserirvine.dvdlibrary.dao;

import org.fraserirvine.dvdlibrary.dto.DVD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DVDLibraryDaoFileImplTest {

    DVDLibraryDao testDao;



    @BeforeEach
    public void setUp() throws Exception {
        testDao = new DVDLibraryDaoFileImpl();
        new FileWriter("test");

        testDao.setPath("test");
    }


    @Test
    void addDVD() throws Exception {

        String dvdId = "0001";
        DVD dvd = new DVD(dvdId);
        dvd.setTitle("The Wolf of Wall Street");
        dvd.setReleaseDate("2013-12-17");
        dvd.setRating("R");
        dvd.setDirectorName("Martin Scorsese");
        dvd.setStudio("Red Granite Pictures");
        dvd.setUserRating("N/A");


        System.out.println(testDao.getPath());

        testDao.addDVD(dvdId,dvd);

        DVD retrievedDVD = testDao.getSingleDVD(dvdId);

        assertEquals(
                dvd.getDvdId(),
                retrievedDVD.getDvdId(),
                "Checking DVD Id"
        );
        assertEquals(
                dvd.getTitle(),
                retrievedDVD.getTitle(),
                "Checking DVD Title"
        );
        assertEquals(
                dvd.getReleaseDate(),
                retrievedDVD.getReleaseDate(),
                "Checking DVD release date"
        );
        assertEquals(
                dvd.getRating(),
                retrievedDVD.getRating(),
                "Checking DVD Rating"
        );
        assertEquals(
                dvd.getDirectorName(),
                retrievedDVD.getDirectorName(),
                "Checking DVD Director Name"
        );
        assertEquals(
                dvd.getStudio(),
                retrievedDVD.getStudio(),
                "Checking DVD Studio"
        );
        assertEquals(
                dvd.getUserRating(),
                retrievedDVD.getUserRating(),
                "Checking User rating"
        );
    }

    @Test
    void removeDVD() throws Exception{

        DVD dvdOne = new DVD("0001");
        dvdOne.setTitle("The Wolf of Wall Street");
        dvdOne.setReleaseDate("2013-12-17");
        dvdOne.setRating("R");
        dvdOne.setDirectorName("Martin Scorsese");
        dvdOne.setStudio("Red Granite Pictures");
        dvdOne.setUserRating("N/A");

        DVD dvdTwo = new DVD("0002");
        dvdTwo.setTitle("Morbius");
        dvdTwo.setReleaseDate("2022-03-10");
        dvdTwo.setRating("R");
        dvdTwo.setDirectorName("Daniel Espinosa");
        dvdTwo.setStudio("Marvel Entertainment");
        dvdTwo.setUserRating("N/A");

        testDao.addDVD(dvdOne.getDvdId(), dvdOne);
        testDao.addDVD(dvdTwo.getDvdId(), dvdTwo);

        //remove the first movie

        DVD removedDVD = testDao.removeDVD(dvdOne.getDvdId());

        assertEquals(
                removedDVD.toString(),
                dvdOne.toString(),
                "The removed dvd should be wolf of wallstreet"
        );

        List<DVD> allDVD = testDao.listDVDs();

        assertNotNull(
                allDVD,
                "All DVDs list shoud not be null"
        );
        assertEquals(
                1,
                allDVD.size(),
                "all DVD should only have 1 DVD"
        );

        assertFalse(
                allDVD.contains(dvdOne),
                "All DVDs should not include Wolf of wall street"
        );
        assertTrue(
                allDVD.contains(dvdTwo),
                "All DVD should not include morbius"
        );

    }

    @Test
    void listDVDs() throws Exception {

        DVD dvdOne = new DVD("0001");
        dvdOne.setTitle("The Wolf of Wall Street");
        dvdOne.setReleaseDate("2013-12-17");
        dvdOne.setRating("R");
        dvdOne.setDirectorName("Martin Scorsese");
        dvdOne.setStudio("Red Granite Pictures");
        dvdOne.setUserRating("N/A");

        DVD dvdTwo = new DVD("0002");
        dvdTwo.setTitle("Morbius");
        dvdTwo.setReleaseDate("2022-03-10");
        dvdTwo.setRating("R");
        dvdTwo.setDirectorName("Daniel Espinosa");
        dvdTwo.setStudio("Marvel Entertainment");
        dvdTwo.setUserRating("N/A");

        testDao.addDVD(dvdOne.getDvdId(), dvdOne);
        testDao.addDVD(dvdTwo.getDvdId(), dvdTwo);

        List<DVD> allDVD = testDao.listDVDs();

        assertNotNull(
                allDVD,
                "the list of DVD must not be null"
        );
        assertEquals(
                2,
                allDVD.size(),
                "List of DVD should have 2 DVDs"
        );

        assertTrue(
                allDVD.contains(dvdOne),
                "the list of DVD should include wolf of wall street"
        );
        assertTrue(
                allDVD.contains(dvdTwo),
                "The list of dvd should include morbius"
        );

    }

    @Test
    void getSingleDVD() {


    }

    @Test
    void searchDVD() throws Exception {

        DVD dvdOne = new DVD("0001");
        dvdOne.setTitle("The Wolf of Wall Street");
        dvdOne.setReleaseDate("2013-12-17");
        dvdOne.setRating("R");
        dvdOne.setDirectorName("Martin Scorsese");
        dvdOne.setStudio("Red Granite Pictures");
        dvdOne.setUserRating("N/A");

        DVD dvdTwo = new DVD("0002");
        dvdTwo.setTitle("Morbius");
        dvdTwo.setReleaseDate("2022-03-10");
        dvdTwo.setRating("R");
        dvdTwo.setDirectorName("Daniel Espinosa");
        dvdTwo.setStudio("Marvel Entertainment");
        dvdTwo.setUserRating("N/A");

        testDao.addDVD(dvdOne.getDvdId(), dvdOne);
        testDao.addDVD(dvdTwo.getDvdId(), dvdTwo);

        //search by full name of wolf of wall street
        List<DVD> searchOne = testDao.searchByTitle("The Wolf of Wall Street");

        assertEquals(
                1,
                searchOne.size(),
                "There should only be one search return"
        );
        assertTrue(
                searchOne.contains(dvdOne),
                "This search should return wolf of wall street"
        );

        //search by partial name of wolf of wall street

        List<DVD> searchTwo = testDao.searchByTitle("The Wolf");

        assertEquals(
                1,
                searchTwo.size(),
                "there should only be one search return"
        );
        assertTrue(
                searchTwo.contains(dvdOne),
                "This search should return wolf of wall street"
        );

        // broad search by having an empty search query

        List<DVD> searchThree = testDao.searchByTitle("");

        assertEquals(
                2,
                searchThree.size(),
                "There should be two search returns"
        );
    }

    @Test
    void loadDVD() {

        testDao.setPath("testpath");

        String thePath = testDao.getPath();

        assertEquals(
                thePath,
                "testpath",
                "the path names do not match"
        );

    }

    @Test
    void getPath() {
    }

    @Test
    void setPath() {
    }
}