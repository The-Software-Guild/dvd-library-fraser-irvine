package org.fraserirvine.dvdlibrary.dto;

import java.util.Date;

public class DVD {

    private String dvdId;
    private String title;
    private Date releaseDate;
    private String rating;
    private String directorName;
    private String studio;
    private String userRating;

    public DVD(String dvdId) {
        this.dvdId = dvdId;
    }

    public String getDvdId() {
        return dvdId;
    }

    public void setDvdId(String dvdId) {
        this.dvdId = dvdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }
}
