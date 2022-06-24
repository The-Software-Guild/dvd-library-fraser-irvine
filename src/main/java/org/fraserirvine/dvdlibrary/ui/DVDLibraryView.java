package org.fraserirvine.dvdlibrary.ui;

import org.fraserirvine.dvdlibrary.dto.DVD;

import java.time.LocalDate;
import java.util.List;

public class DVDLibraryView {

    private UserIO io;

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection(String path) {
        io.print("Main Menu");
        printCurrentPath(path);
        io.print("1. Add DVD");
        io.print("2. Remove DVD");
        io.print("3. Edit DVD");
        io.print("4. List DVDs");
        io.print("5. Show DVD");
        io.print("6. Search DVDs");
        io.print("7. Load DVD");
        io.print("8. Exit Program");

        return io.readInt("Please select from the above choices", 1, 8);

    }

    public int printEditMenuAndGetSelection() {
        io.print("=== Edit Menu ===");
        io.print("What would you like to edit?");
        io.print("1. Title");
        io.print("2. Release Date");
        io.print("3. Rating");
        io.print("4. Director Name");
        io.print("5. Studio Name");
        io.print("6. User Rating");
        io.print("7. Cancel");

        return io.readInt("Please select from the above choices", 1, 7);
    }

    //
    // edit options
    //


    public boolean printPromptKeepEditingAndGetSelection() {
        while (true) {
            String userInput = io.readString("Would you like to keep editing? [y/n]");
            if (userInput.equals("y")) {
                return true;
            } else if (userInput.equals("n")) {
                return false;
            } else {
                displayErrorMessage("invalid selection");
            }
        }
    }

    public DVD editDVDTitle(DVD dvd) {
        dvd.setTitle(io.readString(displayEditingElement("Title")));
        return dvd;
    }

    public DVD editDVDRelease(DVD dvd) {
        dvd.setReleaseDate(io.readDate(displayEditingElement("Release Date")));
        return dvd;
    }

    public DVD editDVDRating(DVD dvd) {
        dvd.setRating(io.readString(displayEditingElement("Rating")));
        return dvd;
    }

    public DVD editDVDDirectorName(DVD dvd) {
        dvd.setDirectorName(io.readString(displayEditingElement("Director Name")));
        return dvd;
    }

    public DVD editDVDStudioName(DVD dvd) {
        dvd.setStudio(io.readString(displayEditingElement("Studio Name")));
        return dvd;
    }

    public DVD editDVDUserRating(DVD dvd) {
        dvd.setUserRating(io.readStringAllowEmpty(displayEditingElement("User Rating")));
        return dvd;
    }

    public String displayEditingElement(String element) {
        return "Edit " + element + " to: ";
    }


    //
    // User Input
    //

    public String getDVDIdChoice() {
        return io.readString("Please enter DVD ID.");
    }

    public DVD getNewDVDInfo() {
        String dvdId = io.readString("Please enter DVD ID");
        String title = io.readString("Please enter DVD title");
        LocalDate releaseDate = io.readDate("Please enter release Date");
        String rating = io.readString("Please enter rating");
        String directorName = io.readString("Pleaser enter director name");
        String studio = io.readString("Please enter studio name");
        String userRating = io.readStringAllowEmpty("Optional: enter user rating");

        DVD currentDVD = new DVD(dvdId);
        currentDVD.setTitle(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setRating(rating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);
        return currentDVD;
    }

    public void displayDVDWithPrompt(DVD dvd) {
        if (dvd != null) {
            io.print("ID: " + dvd.getDvdId());
            io.print("Title: " + dvd.getTitle());
            io.print("Release Date: " + dvd.getReleaseDate());
            io.print("Rating: " + dvd.getRating());
            io.print("Director: " + dvd.getDirectorName());
            io.print("Studio: " + dvd.getStudio());
            io.print("User Rating: " + dvd.getUserRating());
        } else {
            io.print("DVD not found");
        }
        io.readString("Please hit enter to continue");
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print("ID: " + dvd.getDvdId());
            io.print("Title: " + dvd.getTitle());
            io.print("Release Date: " + dvd.getReleaseDate());
            io.print("Rating: " + dvd.getRating());
            io.print("Director: " + dvd.getDirectorName());
            io.print("Studio: " + dvd.getStudio());
            io.print("User Rating: " + dvd.getUserRating());
        } else {
            io.print("DVD not found");
        }
    }

    public void displayRemoveResult(DVD dvdRecord) {
        if (dvdRecord != null) {
            io.print("DVD successfully removed");
        } else {
            io.print("DVD not found");
        }
        io.readString("Please hit enter to continue");
    }

    public void displayDVDList(List<DVD> dvdList) {
        dvdList.forEach((dvd) -> {
            io.print(dvd.toString());
        });
    }

    //
    // Search DVD
    //

    public String getSearchParams() {
        return io.readString("Enter DVD Title: ");
    }

    //
    // Load DVD
    //

    public void printCurrentPath(String path) {
        io.print("Current Path: " + path);
    }

    public String getNewLibraryPath() {
        return io.readString("Enter the DVD library path");
    }

    //
    //Banners
    //

    public void displayAddDVDBanner() {
        io.print("=== Add DVD ===");
    }

    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayEditDVDBanner() {
        io.print("=== Edit DVD ===");
    }

    public void displayListDVDBanner() {
        io.print("=== List DVD ===");
    }

    public void displayShowDVDBanner() {
        io.print("=== Show DVD ===");
    }

    public void displaySearchDVDBanner() {
        io.print("=== Search DVD ===");
    }

    public void displayLoadDVDBanner() {
        io.print("=== Load DVD ===");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayExitBanner() {
        io.print("GoodBye!");
    }

    //
    // Success Banners
    //

    public void displayAddDVDSuccessBanner() {
        io.print("Successfully Added DVD");
    }

    public void displayEditDVDSuccessBanner() {
        io.print("Successfully edited DVD");
    }

    public void displayLoadDVDSuccessBanner(String path) {
        io.print("Successfully changed library path to:\n" + path);
    }

}
