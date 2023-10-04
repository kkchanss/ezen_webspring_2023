package day01.webMvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController // 해당 클래스를 restful로 사용하겠다는 선언
public class WebController {

    @GetMapping("/day01/doget") // HTTP가 GET 요청을 했을때
    public List<WebDto> doGet() {
        WebDao webDao = new WebDao();
        return webDao.doGet();
    }

    @PostMapping("/day01/dopost")
    public boolean doPost(String title) {

        // 1. 인수 받아서 DTO 생성
        WebDto webDto = new WebDto(0, title, LocalDate.now(), true);
        WebDao webDao = new WebDao();
        return webDao.doPost(webDto);
    }
}
