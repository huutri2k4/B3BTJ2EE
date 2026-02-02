package com.example.demo.Service;
import com.example.demo.Model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class BookService {
    private List<Book> books = new ArrayList<>();
    public BookService() {
        books.add(new Book(1, "Java Core", "Nguyen Van A"));
        books.add(new Book(2, "Spring Boot", "Tran Van B"));
        books.add(new Book(3, "NodeJS", "Le Van C"));
    }


    public List<Book> getAllBooks() {
        return books;
    }

    // 2. Lấy sách theo ID
    public Book getBookById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // 3. Thêm sách mới
    public void addBook(Book book) {
        books.add(book);
    }

    // 4. Cập nhật sách
    public void updateBook(int id, Book updatedBook) {
        books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .ifPresent(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                });
    }

    // 5. Xóa sách
    public void deleteBook(int id) {
        books.removeIf(book -> book.getId() == id);
    }
}
