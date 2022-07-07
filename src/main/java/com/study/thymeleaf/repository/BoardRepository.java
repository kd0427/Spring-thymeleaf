package com.study.thymeleaf.repository;

import com.study.thymeleaf.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitle(String title); // 구현은 알아서 해준다.
}
