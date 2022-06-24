package org.fraserirvine.dvdlibrary.dao;

import org.fraserirvine.dvdlibrary.dto.DVD;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    public String libraryPath = "";
    public static final String DELIMITER = "::";

    private Map<String, DVD> dvds = new HashMap<>();

    @Override
    public void addDVD(String dvdId, DVD dvd) throws DVDLibraryPersistenceException {
        loadLibrary();
        dvds.put(dvdId, dvd);
        writeLibrary();
    }

    @Override
    public DVD removeDVD(String dvdId) throws DVDLibraryPersistenceException {
        loadLibrary();
        DVD removedDVD = dvds.remove(dvdId);
        writeLibrary();
        return removedDVD;
    }

    @Override
    public DVD editDVD(DVD dvd) throws DVDLibraryPersistenceException {
        loadLibrary();
        DVD newDVD = dvds.put(dvd.getDvdId(), dvd);
        writeLibrary();
        return newDVD;
    }

    @Override
    public List<DVD> listDVDs() throws DVDLibraryPersistenceException {
        loadLibrary();
        return new ArrayList<>(dvds.values());
    }

    @Override
    public DVD getSingleDVD(String dvdId) throws DVDLibraryPersistenceException {
        loadLibrary();
        return dvds.get(dvdId);
    }

    @Override
    public List<DVD> searchDVD(String title) throws DVDLibraryPersistenceException {
        loadLibrary();
        //initialise empty list to hold search returns
        List<DVD> searchReturns = new ArrayList<>();

        //lowercase the search params to ignore casing during logic
        String titleLowCase = title.toLowerCase();

        //iterate through map to look for match
        dvds.forEach((key, value) -> {
            //lower case the dvd title to ignore casing
            String currentTitle = value.getTitle().toLowerCase();
            if (currentTitle.contains(titleLowCase)) {
                searchReturns.add(value);
            }
        });
        return searchReturns;
    }

    @Override
    public void loadDVD(String path) {
        setPath(path);
    }

    private DVD unmarshallDVD(String dvdAsText) {

        String[] dvdTokens = dvdAsText.split(DELIMITER);

        String dvdId = dvdTokens[0];

        DVD dvdFromFile = new DVD(dvdId);

        dvdFromFile.setTitle(dvdTokens[1]);
        dvdFromFile.setReleaseDate(dvdTokens[2]);
        dvdFromFile.setRating(dvdTokens[3]);
        dvdFromFile.setDirectorName(dvdTokens[4]);
        dvdFromFile.setStudio(dvdTokens[5]);
        dvdFromFile.setUserRating(dvdTokens[6]);
        return dvdFromFile;
    }

    private void loadLibrary() throws DVDLibraryPersistenceException {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(libraryPath));
        } catch (IOException e) {
            throw new DVDLibraryPersistenceException("Could not load file",e);
        }

        for (String currentLine : lines) {
            DVD currentDVD = unmarshallDVD(currentLine);
            dvds.put(currentDVD.getDvdId(), currentDVD);
        }
    }

    private String marshallDVD(DVD dvd) {
        String dvdAsText = dvd.getDvdId() + DELIMITER;
        dvdAsText += dvd.getTitle() + DELIMITER;
        dvdAsText += dvd.getReleaseDate() + DELIMITER;
        dvdAsText += dvd.getRating() + DELIMITER;
        dvdAsText += dvd.getDirectorName() + DELIMITER;
        dvdAsText += dvd.getStudio() + DELIMITER;
        dvdAsText += dvd.getUserRating();

        return dvdAsText;
    }

    private void writeLibrary() throws DVDLibraryPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(libraryPath));
        } catch (IOException e) {
            throw new DVDLibraryPersistenceException("Could not save DVD data", e);
        }

        String dvdAsText;

        List<DVD> dvdList = this.listDVDs();
        for (DVD currentDVD : dvdList) {
            dvdAsText = marshallDVD(currentDVD);
            out.println(dvdAsText);
            out.flush();
        }

        out.close();
    }

    // load/save methods

    public String getPath() {
        return libraryPath;
    }

    public void setPath(String path) {
        libraryPath = path;
    }

}
