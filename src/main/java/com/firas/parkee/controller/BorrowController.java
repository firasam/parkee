package com.firas.parkee.controller;

import com.firas.parkee.model.*;
import com.firas.parkee.repository.BookRepository;
import com.firas.parkee.repository.BorrowRepository;
import com.firas.parkee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api")
public class BorrowController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowRepository borrowRepository;

    @PostMapping("/borrows")
    public ResponseEntity<?> borrow(@ModelAttribute BorrowRequestDto borrowRequest) {
        Book book = bookRepository.findById(borrowRequest.getBook_id());
        User user = userRepository.findById(borrowRequest.getUser_id());
        if (user != null && book!= null){
            if (book.isBorrowed()) {
                MessageDto message = new MessageDto();
                message.setMessage("book already borrowed");
                return new ResponseEntity<MessageDto>(message, HttpStatus.OK);
            }
            book.setBorrowed(true);
            bookRepository.save(book);
            Borrow borrow = new Borrow();
            borrow.setBook(book);
            borrow.setUser(user);
            borrow.setBorrowedAt(borrowRequest.getBorrowed_at());
            Borrow newBorrow = borrowRepository.save(borrow);
            return new ResponseEntity<Borrow>(newBorrow,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/return")
    public ResponseEntity<MessageDto> returningBook(@ModelAttribute ReturnRequestDto returnRequest) {
        Borrow borrow = borrowRepository.findTopByUserIdAndBookIdOrderByBorrowedAtDesc(returnRequest.getUser_id(),returnRequest.getBook_id());
        Book book = bookRepository.findById(returnRequest.getBook_id());
        if (borrow!=null && !borrow.isReturned()){
            long millisecondBorrow = borrow.getBorrowedAt().getEpochSecond();
            long millisecondReturn = returnRequest.getReturned_at().getEpochSecond();
            long diff = millisecondReturn - millisecondBorrow;
            long diffDays = diff / (24 * 60 * 60);
            System.out.println("TIME "+ diffDays);
            borrow.setReturned(true);
            book.setBorrowed(false);
            bookRepository.save(book);
            borrowRepository.save(borrow);
            if (diffDays < 7){
                MessageDto message = new MessageDto();
                message.setMessage("return on time");
                return new ResponseEntity<MessageDto>(message, HttpStatus.OK);
            }else{
                MessageDto message = new MessageDto();
                message.setMessage("return late");
                return new ResponseEntity<MessageDto>(message, HttpStatus.OK);
            }
        }

        MessageDto message = new MessageDto();
        message.setMessage("borrow not found");
        return new ResponseEntity<MessageDto>(message, HttpStatus.NOT_FOUND);
    }
}
