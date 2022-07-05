package org.fraserirvine.dvdlibrary;

import org.fraserirvine.dvdlibrary.controller.DVDLibraryController;
import org.fraserirvine.dvdlibrary.dao.DVDLibraryDao;
import org.fraserirvine.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import org.fraserirvine.dvdlibrary.service.DVDLibraryServiceLayer;
import org.fraserirvine.dvdlibrary.service.DVDLibraryServiceLayerImpl;
import org.fraserirvine.dvdlibrary.ui.DVDLibraryView;
import org.fraserirvine.dvdlibrary.ui.UserIO;
import org.fraserirvine.dvdlibrary.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DVDLibraryController controller = ctx.getBean("controller", DVDLibraryController.class);
        controller.run();
    }

}
