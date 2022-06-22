package org.fraserirvine.dvdlibrary.controller;

import org.fraserirvine.dvdlibrary.dao.DVDLibraryDao;
import org.fraserirvine.dvdlibrary.dao.DVDLibraryDaoException;
import org.fraserirvine.dvdlibrary.dto.DVD;
import org.fraserirvine.dvdlibrary.ui.DVDLibraryView;

public class DVDLibraryController {

    private DVDLibraryView view;
    private DVDLibraryDao dao;

    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
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
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
        view.displayExitBanner();
    }

    private int getMenuSelection() {
        String currentPath = dao.getPath();
        return view.printMenuAndGetSelection(currentPath);
    }

    private int getEditMenuSelection() {
        return view.printEditMenuAndGetSelection();
    }

    private void addDVD() throws DVDLibraryDaoException {
        view.displayAddDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getDvdId(), newDVD);
        view.displayAddDVDSuccessBanner();
    }

    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String dvdId = view.getDVDIdChoice();
        DVD dvd = dao.removeDVD(dvdId);
        view.displayRemoveResult(dvd);
    }

    private void editDVD() throws DVDLibraryDaoException {
        view.displayEditDVDBanner();
        String dvdId = view.getDVDIdChoice();
        DVD dvd = dao.getSingleDVD(dvdId);
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
        dao.editDVD(dvdEdit);
        view.displayEditDVDSuccessBanner();
    }

    private void listDVDs() throws DVDLibraryDaoException {
        view.displayListDVDBanner();
        view.displayDVDList(dao.listDVDs());
    }

    private void showDVD() throws DVDLibraryDaoException {
        view.displayShowDVDBanner();
        String dvdId = view.getDVDIdChoice();
        DVD dvd = dao.getSingleDVD(dvdId);
        view.displayDVD(dvd);
    }

    private void searchDVDs() throws DVDLibraryDaoException {
        view.displaySearchDVDBanner();
        String searchParams = view.getSearchParams();
        view.displayDVDList(dao.searchDVD(searchParams));
    }

    private void loadDVD() {
        view.displayLoadDVDBanner();
        String newPath = view.getNewLibraryPath();
        dao.loadDVD(newPath);
        view.displayLoadDVDSuccessBanner(newPath);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

}
