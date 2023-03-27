package controller;

import java.util.List;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable int id) {
        return bookService.findById(id);
    }

    @PostMapping("")
    public void save(@RequestBody Book book) {
        bookService.save(book);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Book book) {
        book.setId(id);
        bookService.update(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        bookService.delete(id);
    }
}
