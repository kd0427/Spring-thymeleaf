package com.study.thymeleaf.controller;

import com.study.thymeleaf.model.Board;
import com.study.thymeleaf.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") //CORS CSRF
@RequestMapping("/api")
public class BoardRestApiController {

    @Autowired
    private BoardRepository repository;


    @GetMapping("/boards")
    List<Board> all(@RequestParam(required = false) String title) {

        if(StringUtils.isEmpty(title)){
            return repository.findAll();
        }else{
            return repository.findByTitle(title);
        }

    }
    // end::get-aggregate-root[]

    @PostMapping("/boards")
    Board newBoard(@RequestBody Board newBoard) {
        return repository.save(newBoard);
    }

    // Single item

    @GetMapping("/boards/{id}")
    Board one(@PathVariable Optional<Long> id) {
        log.info(":::::::::: {}", id.get());
        return repository.findById(id.get()).orElse(null);
    }

    @PutMapping("/boards/{id}")
    Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long id) {

        return repository.findById(id)
                .map(Board -> {
                    Board.setTitle(newBoard.getTitle());
                    Board.setContent(newBoard.getContent());
                    return repository.save(Board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return repository.save(newBoard);
                });
    }

    @DeleteMapping("/boards/{id}")
    void deleteBoard(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
