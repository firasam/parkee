package com.firas.parkee.repository;

import com.firas.parkee.model.Book;
import com.firas.parkee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
}
