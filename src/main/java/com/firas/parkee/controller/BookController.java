package com.firas.parkee.controller;

import com.firas.parkee.model.Book;
import com.firas.parkee.model.User;
import com.firas.parkee.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

  @Autowired
  private BookRepository bookRepository;

  @GetMapping
  public List<Book> getAllBook() {
    return bookRepository.findAll();
  } 

  @GetMapping("/{id}")
  public Book getBookById(@PathVariable Long id) {
    return bookRepository.findById(id).get();
  }

  @PostMapping
  public Book createBook(@ModelAttribute Book Book) {
    return bookRepository.save(Book);
  }
}
