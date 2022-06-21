package org.fraserirvine.dvdlibrary.ui;

public class DVDRosterView {

    private UserIO io;

    public DVDRosterView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add DVD");
        io.print("2. Remove DVD");
        io.print("3. Edit DVD");
        io.print("4. List DVDs");
        io.print("5. Show DVD");
        io.print("6. Search DVDs");
        io.print("7. Load DVD");

        return io.readInt("Please select from the above choices", 1, 7);

    }



}
