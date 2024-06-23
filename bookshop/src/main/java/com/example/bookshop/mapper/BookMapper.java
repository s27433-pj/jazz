package com.example.bookshop.mapper;

import com.example.bookshop.dto.BookDTO;
import com.example.bookshop.model.Book;

public class BookMapper {

    private static com.example.bookshop.model.BookDTO BookDTO;

    public static com.example.bookshop.model.BookDTO toDTO(Book book) {
        if (book == null) {
            return null;
        }
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor().getName());
        bookDTO.setPrice(book.getPrice());
        return BookDTO;
    }

    public static Book toEntity(BookDTO bookDTO) {
        if (bookDTO == null) {
            return null;
        }
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setPrice(bookDTO.getPrice());
        return book;
    }
}
