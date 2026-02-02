package com.example.demo.Controller;

import com.example.demo.Model.Book;
import com.example.demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Đổi từ @RestController sang @Controller để dùng Thymeleaf
@RequestMapping("/books") // Đổi path từ /api/books thành /books cho dễ dùng web
public class BookController {

    @Autowired
    private BookService bookService;

    // Hiển thị danh sách sách (books.html)
    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    // Trang hiển thị form thêm mới (add-book.html)
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    // Xử lý thêm sách mới
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "redirect:/books"; // Sau khi thêm thì quay về trang danh sách
    }

    // Trang hiển thị form sửa (edit.html)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "edit";
    }

    // Xử lý cập nhật sách
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable int id, @ModelAttribute("book") Book updatedBook) {
        bookService.updateBook(id, updatedBook);
        return "redirect:/books";
    }

    // Xử lý xóa sách
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}