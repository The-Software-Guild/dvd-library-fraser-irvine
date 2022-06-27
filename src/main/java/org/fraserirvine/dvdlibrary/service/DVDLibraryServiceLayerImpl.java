package org.fraserirvine.dvdlibrary.service;

import org.fraserirvine.dvdlibrary.dao.DVDLibraryPersistenceException;
import org.fraserirvine.dvdlibrary.dao.DVDLibraryDao;
import org.fraserirvine.dvdlibrary.dto.DVD;

import java.util.List;

public class DVDLibraryServiceLayerImpl implements DVDLibraryServiceLayer {

    DVDLibraryDao dao;

    public DVDLibraryServiceLayerImpl(DVDLibraryDao dao) {
        this.dao = dao;
    }


    @Override
    public void addDVD(DVD dvd) throws
            DVDLibraryDataValidationException,
            DVDLibraryPersistenceException,
            DVDLibraryDuplicateIdException{

        if (dao.getSingleDVD(dvd.getDvdId()) != null) {
            throw new DVDLibraryDuplicateIdException(
                    "Error: could not create DVD. DVD Id "
                    + dvd.getDvdId()
                    + " already exists"
            );
        }

        validateDVDData(dvd);

        dao.addDVD(dvd.getDvdId(), dvd);
    }

    @Override
    public DVD removeDVD(String dvdId) throws DVDLibraryPersistenceException {
        DVD removedDVD = dao.removeDVD(dvdId);
        return removedDVD;
    }

    @Override
    public DVD editDVD(DVD dvd) throws DVDLibraryPersistenceException {
        return dao.editDVD(dvd);
    }

    @Override
    public List<DVD> listDVDs() throws DVDLibraryPersistenceException{
        return dao.listDVDs();
    }

    @Override
    public DVD getSingleDVD(String dvdId) throws DVDLibraryPersistenceException {
        return dao.getSingleDVD(dvdId);
    }

    @Override
    public List<DVD> searchDVD(String title) throws DVDLibraryPersistenceException {
        return dao.searchDVD(title);
    }

    @Override
    public void loadDVD(String path) {
        dao.loadDVD(path);
    }

    @Override
    public String getPath() {
        return dao.getPath();
    }

    private void validateDVDData(DVD dvd) throws DVDLibraryDataValidationException {
        if (
                dvd.getTitle() == null
                ||dvd.getTitle().trim().length() == 0
                ||dvd.getReleaseDate() == null
                ||dvd.getRating() == null
                ||dvd.getDirectorName() == null
                ||dvd.getDirectorName().trim().length() == 0
                ||dvd.getStudio() == null
                ||dvd.getStudio().trim().length() == 0
        ) {
            throw new DVDLibraryDataValidationException(
                    "Error: All fields [Title,ReleaseDate,Rating,Director Name,Studio] are required"
            );
        }
    }

}
