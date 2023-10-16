package example.day06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Controller 사용처 웹 : JS(AJAX), REACT(AXIOS), 앱, 소프트웨어
// 역할 : AJAX[외부인] <----- 연결다리[자바] -----> 서비스[자바] ---- repository --- entity ---> DB table
// 식당 예시
// 예시 : 손님( 주문요청 / 주문응답 ) <--대화/행위--- 서빙 ---대화/행위--> 요리사 --대화/행위---> 냉장고
//
@RestController
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping("/do")
    // 1. C
    public boolean bWrite(@RequestBody NoteDto noteDto) {
        return noteService.bWrite(noteDto);
    }

    @GetMapping("/do")
    // 2. R
    public List<NoteDto> bList() {

        List<NoteDto> result = noteService.bList();

        return result;
    }

    @PutMapping("/do")
    // 3. U
    public boolean bUpdate(@RequestBody NoteDto noteDto) {
        return noteService.bUpdate(noteDto);
    }

    @DeleteMapping("/do")
    // 4. D
    public boolean bDelete(@RequestParam int no) {

        return noteService.bDelete(no);
    }
}
