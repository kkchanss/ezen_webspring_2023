package example.task.Phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {

    @Autowired
    private PhoneEntityRepository phoneEntityRepository;

    public boolean doPost(@RequestBody PhoneDto phoneDto) {

        PhoneEntity phoneEntity = PhoneEntity.builder()
                .pno(phoneDto.getPno())
                .pname(phoneDto.getPname())
                .pphone(phoneDto.getPphone())
                .build();

        phoneEntityRepository.save(phoneEntity);
        return true;
    }

    public List<PhoneDto> doGet() {

        List<PhoneEntity> entityList = phoneEntityRepository.findAll();

        List<PhoneDto> list = new ArrayList<>();

        entityList.forEach((entity) -> {
            PhoneDto phoneDto = PhoneDto.builder()
                    .pno(entity.getPno())
                    .pname(entity.getPname())
                    .pphone(entity.getPphone())
                    .build();
            list.add(phoneDto);
        });
        return list;
    }

    @Transactional
    public boolean doPut(@RequestBody PhoneDto phoneDto) {

        Optional<PhoneEntity> phoneEntity = phoneEntityRepository.findById(phoneDto.getPno());

        if(phoneEntity.isPresent()) {

            PhoneEntity updateEntity = phoneEntity.get();

            updateEntity.setPphone(phoneDto.getPphone());
            updateEntity.setPname(phoneDto.getPname());
        }
        return true;
    }

    public boolean doDelete(@RequestParam int pno) {
        phoneEntityRepository.deleteById(pno);
        return true;
    }
}
