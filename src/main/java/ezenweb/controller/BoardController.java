package ezenweb.controller;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.PageDto;
import ezenweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("") // 게시물 등록
    public boolean write(BoardDto boardDto) {

        return boardService.write(boardDto);
    }

    @GetMapping("") // 게시물 해당 페이지 출력
    public PageDto getAll(
            @RequestParam int page,
            @RequestParam String key,
            @RequestParam String keyword,
            @RequestParam int size) {

        return boardService.getAll(page, key, keyword, size);
    }

    @PutMapping("") // 게시물 수정
    public boolean update(BoardDto boardDto) {

        return boardService.update(boardDto);
    }

    @DeleteMapping("") // 게시물 삭제
    public boolean delete(@RequestParam int bno) {

        return boardService.delete(bno);
    }

    @GetMapping("/one") // 하나만 가져오기
    public BoardDto getOne(@RequestParam int bno) {
        return boardService.getOne(bno);
    }

}
