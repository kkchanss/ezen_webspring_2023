package example.day05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/index")
    public Resource getIndex() {
        return new ClassPathResource("templates/todo.html");
    }

    @PostMapping("")
    public boolean doPost(@RequestBody TodoDto todoDto) {
        System.out.println("doPost");
        return todoService.doPost(todoDto);
    }

    @GetMapping("")
    public List<TodoDto> doGet() {

        return todoService.doGet();
    }

    @PutMapping("")
    public boolean doPut(@RequestBody TodoDto todoDto) {

        return todoService.doPut(todoDto);
    }

    @DeleteMapping("")
    public boolean doDelete(@RequestParam int tno) {

        return todoService.doDelete(tno);
    }
}
