package org.fraserirvine.dvdlibrary;

import org.fraserirvine.dvdlibrary.controller.DVDLibraryController;
import org.fraserirvine.dvdlibrary.dao.DVDLibraryDao;
import org.fraserirvine.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import org.fraserirvine.dvdlibrary.service.DVDLibraryServiceLayer;
import org.fraserirvine.dvdlibrary.service.DVDLibraryServiceLayerImpl;
import org.fraserirvine.dvdlibrary.ui.DVDLibraryView;
import org.fraserirvine.dvdlibrary.ui.UserIO;
import org.fraserirvine.dvdlibrary.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryServiceLayer myService = new DVDLibraryServiceLayerImpl(myDao);
        DVDLibraryController controller = new DVDLibraryController(myService,myView);
        controller.run();
    }

}
