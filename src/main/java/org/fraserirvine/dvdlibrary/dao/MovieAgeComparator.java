package org.fraserirvine.dvdlibrary.dao;

import org.fraserirvine.dvdlibrary.dto.DVD;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class MovieAgeComparator implements Comparator<DVD> {

    public int compare(DVD dvd1, DVD dvd2) {
        if (dvd1.equals(dvd2)) {
            return 0;
        } else if (ChronoUnit.DAYS.between(dvd1.getReleaseDate(),LocalDate.now()) < ChronoUnit.DAYS.between(dvd2.getReleaseDate(),LocalDate.now())) {
            return 1;
        } else {
            return -1;
        }
    }

}
