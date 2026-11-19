package com.example.library_management.Controller;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;

import com.example.library_management.model.Book;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/books")
public class BookController {

    ArrayList<Book> books = new ArrayList<>();

    // Add Book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        books.add(book);
        return ResponseEntity.ok(book);
    }

    // Get All Books
    @GetMapping
    public ArrayList<Book> getAllBooks() {
        return books;
    }

    // Get Book By ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Integer id) {
        for (Book b : books) {
            if (b.getId().equals(id)) {
                return ResponseEntity.ok(b);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // Update Book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Integer id,
            @RequestBody Book newBook) {

        for (Book b : books) {
            if (b.getId().equals(id)) {
                b.setTitle(newBook.getTitle());
                b.setAuthor(newBook.getAuthor());
                b.setCategory(newBook.getCategory());
                b.setAvailableCopies(newBook.getAvailableCopies());

                return ResponseEntity.ok(b);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // Delete Book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {

        boolean deleted = books.removeIf(b -> b.getId().equals(id));

        if (deleted) {
            return ResponseEntity.ok("Book Deleted Successfully");
        }

        return ResponseEntity.status(404).body("Book Not Found");
    }
}