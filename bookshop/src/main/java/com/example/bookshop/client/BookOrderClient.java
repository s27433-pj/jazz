package com.example.bookshop.client;

import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "bookOrderService", url = "http://localhost:8080/order")
public interface BookOrderClient {

    @PostMapping
    void sendBookOrders(@RequestBody List<BookOrder> bookOrders);

    @GetMapping
    List<BookOrder> getAllOrders();
    @Data
    class BookOrder {
        private Long bookId;
        private String title;
        private int visitCount;

        public BookOrder(Long bookId, String title, int visitCount) {
            this.bookId = bookId;
            this.title = title;
            this.visitCount = visitCount;
        }
    }
}
