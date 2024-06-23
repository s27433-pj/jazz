package com.example.bookshop.controller;

import com.example.bookshop.client.BookOrderClient;
import com.example.bookshop.service.OrderService;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookOrderClient bookOrderClient;

    @GetMapping("/order-report")
    public String generateOrderReport() {
        orderService.generateOrderReport();
        return "Order report generated successfully.";
    }

    @GetMapping("/print")
    public String printOrderReport() throws FileNotFoundException {
        String dest = "order_report.pdf";
        PdfWriter writer = new PdfWriter(dest);
        com.itextpdf.kernel.pdf.PdfDocument pdfDoc = new com.itextpdf.kernel.pdf.PdfDocument(writer);
        Document document = new Document(pdfDoc);

        List<BookOrderClient.BookOrder> orders = bookOrderClient.getAllOrders();

        for (BookOrderClient.BookOrder order : orders) {
            document.add(new Paragraph("Book ID: " + order.getBookId() + ", Title: " + order.getTitle() + ", Visit Count: " + order.getVisitCount()));
        }

        document.close();
        return "PDF created successfully.";
    }
}
