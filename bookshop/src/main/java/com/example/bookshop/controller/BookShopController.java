package com.example.bookshop.controller;
import com.example.bookshop.api.BooksApi;
import com.example.bookshop.mapper.BookMapper;
import com.example.bookshop.model.Book;
import com.example.bookshop.model.BookDTO;
import com.example.bookshop.service.BookShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class BookShopController implements BooksApi {
    private final BookShopService bookShopService;

    public BookShopController(BookShopService bookShopService) {
        this.bookShopService = bookShopService;
    }

    @Override
    public ResponseEntity<String> buyBook(String title) {
        return ResponseEntity.ok(bookShopService.buyBook(title));
    }

    @Override
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookShopService.getAllBooks());
    }

    @Override
    public ResponseEntity<BookDTO> getBookById(Long id) {
        return ResponseEntity.ok(bookShopService.getBookById(id));
    }


    @Override
    public ResponseEntity<List<Book>> getBooksByAuthor(String author) {
       return ResponseEntity.ok(bookShopService.filterBooksByAuthor(author));
    }

    @Override
    public ResponseEntity<List<Book>> getBooksByTitle(String title) {
        return ResponseEntity.ok(bookShopService.filterBooksByTitle(title));
    }
}