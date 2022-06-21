package org.fraserirvine.dvdlibrary.dao;

import org.fraserirvine.dvdlibrary.dto.DVD;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    public static final String LIBRARY_FILE = "libary.txt";
    public static final String DELIMITER = "::";

    private Map<String, DVD> dvds = new HashMap<>();

    @Override
    public DVD addDVD(String dvdId, DVD dvd) {
        return null;
    }

    @Override
    public DVD removeDVD(String dvdId) {
        loadLibrary();
        DVD removedDVD = dvds.remove(dvdId);
        writeLibrary();
        return removedDVD;
    }

    @Override
    public DVD editDVD(DVD newDVD) {
        loadLibrary();
        dvds.put(newDVD.getDvdId(), newDVD);
        writeLibrary();
        return newDVD;
    }

    @Override
    public List<DVD> listDVDs() {
        loadLibrary();
        return new ArrayList<>(dvds.values());
    }

    @Override
    public DVD getSingleDVD(String dvdId) {
        loadLibrary();
        return dvds.get(dvdId);
    }

    @Override
    public DVD searchDVD(String title) {
        return null;
    }

    @Override
    public DVD loadDVD(String path) {
        return null;
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

    private void loadLibrary() {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(LIBRARY_FILE));
        } catch (IOException e) {
            throw new RuntimeException();
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

    private void writeLibrary() {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
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

}
