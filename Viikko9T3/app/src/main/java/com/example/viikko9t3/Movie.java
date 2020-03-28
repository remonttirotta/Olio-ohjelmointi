package com.example.viikko9t3;

import java.util.Date;

public class Movie {

    public Date startDate;
    public Date endDate;
    public String movieTitle;

    public Movie(String title, Date start, Date end){
        movieTitle = title;
        startDate = start;
        endDate = end;
    }

}
