package com.example.android.booklistingapp;

/**
 * Created by Shinam on 15/07/2017.
 */

public class Book {

    public String title;
    public String authors;

    public Book(String title, String authors) {
        this.title = title;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }
}
