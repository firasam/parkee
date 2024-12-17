package com.firas.parkee.repository;

import com.firas.parkee.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    Borrow findTopByUserIdAndBookIdOrderByBorrowedAtDesc(long userId, long bookId);
}
