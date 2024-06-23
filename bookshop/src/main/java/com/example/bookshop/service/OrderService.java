package com.example.bookshop.service;

import com.example.bookshop.client.BookOrderClient;
import com.example.bookshop.model.Book;
import com.example.bookshop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookOrderClient bookOrderClient;

    public void generateOrderReport() {
        List<Book> books = bookRepository.findAll();
        List<BookOrderClient.BookOrder> bookOrders = books.stream()
                .filter(book -> book.getVisitCount() >= 10)
                .map(book -> new BookOrderClient.BookOrder(book.getId(), book.getTitle(), book.getVisitCount()))
                .collect(Collectors.toList());

        bookOrderClient.sendBookOrders(bookOrders);
    }
}
