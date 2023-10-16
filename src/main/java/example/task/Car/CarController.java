package example.task.Car;

import example.day06.NoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    // 1. C
    @PostMapping("")
    public boolean doPost(@RequestBody CarDto carDto ){
        boolean result = carService.doPost(carDto);
        return result;
    }
    // 2. R
    @GetMapping("")
    public List<CarDto> doGet(){
        List<CarDto> result = carService.doGet();
        return result;
    }
    // 3. U
    @PutMapping("")
    public boolean doPut(@RequestBody CarDto carDto){
        boolean result = carService.doPut( carDto );
        return result;
    }
    // 4. D
    @DeleteMapping("")
    public boolean doDelete(@RequestParam int cno ){
        boolean result = carService.doDelete(cno);
        return result;
    }
}