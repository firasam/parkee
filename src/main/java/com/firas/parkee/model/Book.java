package com.firas.parkee.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_borrowed", columnDefinition = "boolean default false")
    private Boolean isBorrowed = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Borrow> borrowerList;

    public Long getId() {
        return id;
    }

    public Boolean getBorrowed() {
        return isBorrowed;
    }

    public List<Borrow> getBorrowerList() {
        return borrowerList;
    }

    public void setBorrowerList(List<Borrow> borrowerList) {
        this.borrowerList = borrowerList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(Boolean borrowed) {
        isBorrowed = borrowed;
    }
}
