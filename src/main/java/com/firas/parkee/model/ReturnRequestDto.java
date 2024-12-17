package com.firas.parkee.model;

import java.sql.Timestamp;
import java.time.Instant;

public class ReturnRequestDto {
    private int user_id;
    private int book_id;
    private java.time.Instant returned_at;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Instant getReturned_at() {
        return returned_at;
    }

    public void setReturned_at(Instant returned_at) {
        this.returned_at = returned_at;
    }
}
