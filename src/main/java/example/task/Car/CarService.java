package example.task.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service

public class CarService {

    @Autowired
    private CarEntityRepository carEntityRepository;
    @Transactional
    public boolean doPost(@RequestBody CarDto carDto) {
        carDto.setCdate(LocalDateTime.now());

        carEntityRepository.save(carDto.toEntity());
        return true;
    }
    @Transactional
    public List<CarDto> doGet() {

        List<CarEntity> entityList = carEntityRepository.findAll();

        List<CarDto> list = new ArrayList<>();

        entityList.forEach(entity -> {
            list.add(entity.toDto());
        });

        Collections.sort(list, new CarCdateComparator());

        return list;
    }

    @Transactional
    public boolean doPut(@RequestBody CarDto carDto) {

        Optional<CarEntity> carEntity = carEntityRepository.findById(carDto.getCno());

        if(carEntity.isPresent()) {

            CarEntity updateEntity = carEntity.get();

            updateEntity.setCtype(carDto.getCtype());
            updateEntity.setCid(carDto.getCid());

        }
        return true;
    }
    @Transactional
    public boolean doDelete(@RequestParam int cno) {
        carEntityRepository.deleteById(cno);
        return true;
    }
}

class CarCdateComparator implements Comparator<CarDto> {

    @Override
    public int compare(CarDto o1, CarDto o2) {
        if (o1.getCdate().isAfter(o2.getCdate())) {
            return 1;
        } else if (o2.getCdate().isAfter(o1.getCdate())) {
            return -1;
        }
        return 0;
    }
}