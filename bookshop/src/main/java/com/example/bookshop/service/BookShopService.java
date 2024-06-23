package com.example.bookshop.service;

import com.example.bookshop.mapper.BookMapper;
import com.example.bookshop.model.Author;
import com.example.bookshop.model.Book;
import com.example.bookshop.model.BookDTO;
import com.example.bookshop.repository.AuthorRepository;
import com.example.bookshop.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookShopService {

    @Autowired
    private BookRepository bookRepository;
    private BookMapper bookMapper;

    @Autowired
    private AuthorRepository authorRepository;

    @PostConstruct
    public void init() {
        Author authorA = new Author(null, "Author A");
        Author authorB = new Author(null, "Author B");
        Author joshuaBloch = new Author(null, "Joshua Bloch");
        Author robertCMartin = new Author(null, "Robert C. Martin");


        authorRepository.save(authorA);
        authorRepository.save(authorB);
        authorRepository.save(joshuaBloch);
        authorRepository.save(robertCMartin);

        bookRepository.save(new Book(null, "Java Programming", "Programming", 29.99, 300, 0, 10, true, authorA));
        bookRepository.save(new Book(null, "Python Programming", "Programming", 24.99, 250, 0, 8, true, authorB));
        bookRepository.save(new Book(null, "Effective Java", "Programming", 39.99, 400, 0, 5, true, joshuaBloch));
        bookRepository.save(new Book(null, "Clean Code", "Programming", 34.99, 350, 0, 7, true, robertCMartin));
        bookRepository.save(new Book(null, "Clean Code", "Programming", 34.99, 350, 20, 7, true, robertCMartin));
        bookRepository.save(new Book(null, "Clean Code", "Programming", 34.99, 350, 15, 7, true, robertCMartin));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> filterBooksByTitle(String title) {
        List<Book> books = bookRepository.findAll().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());

        books.forEach(Book::incrementVisitCount);
        bookRepository.saveAll(books);


        return books;
    }

    public List<Book> filterBooksByAuthor(String authorName) {
        List<Book> books = bookRepository.findAll().stream()
                .filter(book -> book.getAuthor().getName().equalsIgnoreCase(authorName))
                .collect(Collectors.toList());

        books.forEach(Book::incrementVisitCount);
        bookRepository.saveAll(books);

        return books;
    }

    public String buyBook(String title) {
        Optional<Book> optionalBook = bookRepository.findAll().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title) && book.isAvailable())
                .findFirst();

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            if (book.getStock() > 0) {
                book.setStock(book.getStock() - 1);
                book.setAvailable(book.getStock() > 0);
                bookRepository.save(book);
                return "You have successfully bought the book: " + title;
            }
        }

        return "The book is not available or doesn't exist.";
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.getReferenceById(id);
        return bookMapper.toDTO(book);
    }
}
