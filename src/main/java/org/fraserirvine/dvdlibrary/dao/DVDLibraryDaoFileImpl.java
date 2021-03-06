package org.fraserirvine.dvdlibrary.dao;

import org.fraserirvine.dvdlibrary.dto.DVD;
import org.fraserirvine.dvdlibrary.dto.MPAA;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

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

    //
    // Search Methods
    //

    @Override
    public List<DVD> searchByTitle(String title) throws DVDLibraryPersistenceException {
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
    public List<DVD> searchByYear(int duration) throws DVDLibraryPersistenceException {
        loadLibrary();

        LocalDate compareDate = LocalDate.now().minusYears(duration);

        return dvds.values().stream()
                .filter((dvd) -> dvd.getReleaseDate().isAfter(compareDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<DVD> searchByRating(MPAA rating) throws DVDLibraryPersistenceException {
        loadLibrary();

        return dvds.values().stream()
                .filter((dvd) -> dvd.getRating() == rating)
                .collect(Collectors.toList());
    }

    @Override
    public List<DVD> searchByDirector(String director) throws DVDLibraryPersistenceException {
        loadLibrary();
        return dvds.values().stream()
                .filter((dvd) -> dvd.getDirectorName()
                        .toLowerCase()
                        .contains(director.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DVD> searchByStudio(String studio) throws DVDLibraryPersistenceException {
        loadLibrary();
        return dvds.values().stream()
                .filter((dvd) -> dvd.getStudio()
                        .toLowerCase()
                        .contains(studio.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public double getAverageAge() throws DVDLibraryPersistenceException {
        loadLibrary();
        return dvds.values().stream()
                .mapToDouble((dvd) -> ChronoUnit.YEARS.between(dvd.getReleaseDate(),LocalDate.now()))
                .average()
                .getAsDouble();
    }

    @Override
    public DVD getNewestMovie() throws DVDLibraryPersistenceException {
        loadLibrary();
        return dvds.values().stream()
                .max(new MovieAgeComparator())
                .get();
    }

    @Override
    public DVD getOldestMovie() throws DVDLibraryPersistenceException {
        loadLibrary();
        return dvds.values().stream()
                .min(new MovieAgeComparator())
                .get();
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
        dvdFromFile.setRating(unmarshallStringToEnum(dvdTokens[3]));
        dvdFromFile.setDirectorName(dvdTokens[4]);
        dvdFromFile.setStudio(dvdTokens[5]);
        dvdFromFile.setUserRating(dvdTokens[6]);
        return dvdFromFile;
    }

    private MPAA unmarshallStringToEnum(String token) {
        switch (token) {
            case "G":
                return MPAA.G;
            case "PG":
                return MPAA.PG;
            case "PG13":
                return MPAA.PG13;
            case "R":
                return MPAA.R;
            case "NC17":
                return MPAA.NC17;
            default:
                return null;
        }
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

    private String marshallMPAAtoString(MPAA ratingEnum) {
        switch (ratingEnum) {
            case G:
                return "G";
            case PG:
                return "PG";
            case PG13:
                return "PG13";
            case R:
                return "R";
            case NC17:
                return "NC17";
            default:
                return null;
        }
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
