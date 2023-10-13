package example.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("phone")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @GetMapping("/index")
    public Resource getIndex() {
        return new ClassPathResource("templates/phone.html");
    }

    @PostMapping("")
    public boolean doPost(@RequestBody PhoneDto phoneDto) {
        return phoneService.doPost(phoneDto);
    }

    @GetMapping("")
    public List<PhoneDto> doGet() {

        return phoneService.doGet();
    }

    @PutMapping("")
    public boolean doPut(@RequestBody PhoneDto phoneDto) {

        return phoneService.doPut(phoneDto);
    }

    @DeleteMapping("")
    public boolean doDelete(@RequestParam int pno) {
        return phoneService.doDelete(pno);
    }
}
