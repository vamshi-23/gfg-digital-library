package com.library.repo;

import com.library.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {



}
