package org.fraserirvine.dvdlibrary.dao;

import org.fraserirvine.dvdlibrary.dto.DVD;
import org.junit.jupiter.api.*;

import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

class DVDLibraryDaoFileImplTest {

    DVDLibraryDao testDao;


    @BeforeAll
    public static void setUpClass() {}

    @AfterAll
    public static void tearDownClass() {}

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testLibrary.txt";
        new FileWriter(testFile);
        testDao = new DVDLibraryDaoFileImpl();
        testDao.setPath(testFile);
    }

    @AfterEach
    public void tearDown() {}




    @Test
    void addDVD() throws Exception {

        String dvdId = "0001";
        DVD dvd = new DVD(dvdId);
        dvd.setTitle("The Wolf of Wawll Street");
        dvd.setReleaseDate("2013-12-17");
        dvd.setRating("R");
        dvd.setDirectorName("Martin Scorsese");
        dvd.setStudio("Red Granite Pictures");
        dvd.setUserRating(" ");

        testDao.addDVD(dvdId, dvd);

        DVD retrievedDVD = testDao.getSingleDVD(dvdId);

        //check that the data is equal
        assertEquals(
                dvd.getDvdId(),
                retrievedDVD.getDvdId(),
                "Checking DVD id."
        );
        assertEquals(
                dvd.getTitle(),
                retrievedDVD.getTitle(),
                "Checking DVD Title."
        );
        assertEquals(
                dvd.getReleaseDate(),
                retrievedDVD.getReleaseDate(),
                "Checking DVD Release Date."
        );
        assertEquals(
                dvd.getRating(),
                retrievedDVD.getRating(),
                "Checking DVD Rating."
        );
        assertEquals(
                dvd.getDirectorName(),
                retrievedDVD.getDirectorName(),
                "Checking DVD Director Name."
        );
        assertEquals(
                dvd.getStudio(),
                retrievedDVD.getStudio(),
                "Checking DVD Studio Name."
        );
        assertEquals(
                dvd.getUserRating(),
                retrievedDVD.getUserRating(),
                "Checking DVD User Rating."
        );

    }

    @Test
    void removeDVD() {



    }

    @Test
    void editDVD() {
    }

    @Test
    void listDVDs() {
    }

    @Test
    void getSingleDVD() {
    }

    @Test
    void searchDVD() {
    }

    @Test
    void loadDVD() {
    }

    @Test
    void getPath() {
    }
}