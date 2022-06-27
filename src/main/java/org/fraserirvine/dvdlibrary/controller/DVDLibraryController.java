package org.fraserirvine.dvdlibrary.controller;

import org.fraserirvine.dvdlibrary.dao.DVDLibraryPersistenceException;
import org.fraserirvine.dvdlibrary.dto.DVD;
import org.fraserirvine.dvdlibrary.service.DVDLibraryDataValidationException;
import org.fraserirvine.dvdlibrary.service.DVDLibraryDuplicateIdException;
import org.fraserirvine.dvdlibrary.service.DVDLibraryServiceLayer;
import org.fraserirvine.dvdlibrary.ui.DVDLibraryView;

public class DVDLibraryController {

    private DVDLibraryView view;
    private DVDLibraryServiceLayer service;

    public DVDLibraryController(DVDLibraryServiceLayer service, DVDLibraryView view) {
        this.service = service;
        this.view = view;
    }


    public void run() {
        boolean running = true;
        int menuSelection = 0;
        try {
            while (running) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        editDVD();
                        break;
                    case 4:
                        listDVDs();
                        break;
                    case 5:
                        showDVD();
                        break;
                    case 6:
                        searchDVDs();
                        break;
                    case 7:
                        loadDVD();
                        break;
                    case 8:
                        running = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
        } catch (DVDLibraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        view.displayExitBanner();
    }

    private int getMenuSelection() {
        String currentPath = service.getPath();
        return view.printMenuAndGetSelection(currentPath);
    }

    private int getEditMenuSelection() {
        return view.printEditMenuAndGetSelection();
    }

    private void addDVD() throws DVDLibraryPersistenceException {
        view.displayAddDVDBanner();
        boolean hasErrors = false;

        DVD newDVD = view.getNewDVDInfo();
        do {
            try {
                service.addDVD(newDVD);
                view.displayAddDVDSuccessBanner();
                hasErrors = false;
            } catch (DVDLibraryDuplicateIdException | DVDLibraryDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void removeDVD() throws DVDLibraryPersistenceException {
        view.displayRemoveDVDBanner();
        String dvdId = view.getDVDIdChoice();
        DVD dvd = service.removeDVD(dvdId);
        view.displayRemoveResult(dvd);
    }

    private void editDVD() throws DVDLibraryPersistenceException {
        view.displayEditDVDBanner();
        String dvdId = view.getDVDIdChoice();
        DVD dvd = service.getSingleDVD(dvdId);
        //show the DVD thats being edited
        view.displayDVD(dvd);
        boolean keepEditing = true;
        DVD dvdEdit = dvd;
        while (keepEditing) {
            int editSelection = view.printEditMenuAndGetSelection();
            switch (editSelection) {
                case 1:
                    dvdEdit = view.editDVDTitle(dvd);
                    break;
                case 2:
                    dvdEdit = view.editDVDRelease(dvd);
                    break;
                case 3:
                    dvdEdit = view.editDVDRating(dvd);
                    break;
                case 4:
                    dvdEdit = view.editDVDDirectorName(dvd);
                    break;
                case 5:
                    dvdEdit = view.editDVDStudioName(dvd);
                    break;
                case 6:
                    dvdEdit = view.editDVDUserRating(dvd);
                    break;
                case 7:
                    dvdEdit = dvd;
                    break;
            }
            keepEditing = view.printPromptKeepEditingAndGetSelection();
        }
        service.editDVD(dvdEdit);
        view.displayEditDVDSuccessBanner();
    }

    private void listDVDs() throws DVDLibraryPersistenceException {
        view.displayListDVDBanner();
        view.displayDVDList(service.listDVDs());
    }

    private void showDVD() throws DVDLibraryPersistenceException {
        view.displayShowDVDBanner();
        String dvdId = view.getDVDIdChoice();
        DVD dvd = service.getSingleDVD(dvdId);
        view.displayDVD(dvd);
    }

    private void searchDVDs() throws DVDLibraryPersistenceException {
        view.displaySearchDVDBanner();
        String searchParams = view.getSearchParams();
        boolean keepSearching = true;
        while (keepSearching) {
            int searchSelection = view.printSearchMenuAndGetSelection();
            switch (searchSelection) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
            }
        }


        view.displayDVDList(service.searchDVD(searchParams));
    }

    private void loadDVD() {
        view.displayLoadDVDBanner();
        String newPath = view.getNewLibraryPath();
        try {
            service.loadDVD(newPath);
        } catch (DVDLibraryPersistenceException e) {
            throw new RuntimeException(e);
        }
        view.displayLoadDVDSuccessBanner(newPath);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

}
