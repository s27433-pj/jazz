package com.example.bookshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private double price;
    private int pageCount;
    private int visitCount;
    private int stock;
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(String title, String genre, double price, int pageCount, int stock, Author author) {
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.pageCount = pageCount;
        this.stock = stock;
        this.isAvailable = stock > 0;
        this.author = author;
        this.visitCount = 0;
    }

    public void incrementVisitCount() {
        this.visitCount++;
    }
}
