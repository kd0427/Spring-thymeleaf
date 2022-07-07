package com.study.thymeleaf.controller;

import com.study.thymeleaf.model.Board;
import com.study.thymeleaf.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor // 생성자를 만들어줌
@RequestMapping("/board")
public class BoardController {


    private final BoardRepository boardRepository;
//    public BoardController(BoardRepository boardRepository){
//        this.boardRepository =boardRepository;
//    }
    @GetMapping("/list")
    public String list(Model model){
        List<Board> boardList = boardRepository.findAll();
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    @GetMapping("/form/{id}")
    public String write(Model model,@RequestParam(required = false) Long id){
        if(id==null){
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }

        return"board/form";
    }

    @PostMapping("/form")
    public String completed(@Valid Board board, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "board/form";
        }
        boardRepository.save(board);
        return "redirect:/board/list";
    }

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam(required = false) Long id){
        Board board = boardRepository.findById(id).orElse(null); //만약 조회된 값이 없으면 null
        model.addAttribute("board", board);
        return "/board/detail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = false) Long id){
        boardRepository.deleteById(id);

        return "redirect:/board/list";
    }
}
