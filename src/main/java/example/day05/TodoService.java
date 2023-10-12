package example.day05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class TodoService {

    @Autowired
    private TodoEntityRepository todoEntityRepository;

    public boolean doPost(@RequestBody TodoDto todoDto) {

        // 1. DTO를 Entity 반환
            /*
                빌더패턴 사용하는 방법
                클래스명 객체명 = 클래스명.builder()
                                .저장할필드명(저장할 데이터)
                                .저장할필드명(저장할 데이터)
                                .build();
            */

        TodoEntity todoEntity = TodoEntity.builder()
                .tcontent(todoDto.getTcontent())
                .tstate(todoDto.isTstate())
                .build();
        // 2. JPARepositary를 이용한 엔티티 저장 [ insert 대체 ]
        todoEntityRepository.save(todoEntity);
        return false;
    }

    public List<TodoDto> doGet() {

        // 1. 모든 엔티티 호출 [ select 대체 ]
        List<TodoEntity> entityList = todoEntityRepository.findAll();

        // 2. List<TodoEntity> -> List<TodoDto> 반환
        List<TodoDto> list = new ArrayList<>();

        // 엔티티 리스트에서 엔티티 하나씩 꺼내기
        entityList.forEach((entity) -> {
            // 3. 엔티티를 dto로 변환
            TodoDto todoDto = TodoDto.builder()
                    .tno(entity.getTno())
                    .tcontent(entity.getTcontent())
                    .tstate(entity.isTstate())
                    .build();
            list.add(todoDto);
        });
        return list;
    }

    // 트랜잭션 : 최소 단위 일처리 ==> 결과 [ 성공, 실패 ]
    @Transactional // import javax.transaction.Transactional;
    public boolean doPut(@RequestBody TodoDto todoDto) {

        // 1. 수정할 엔티티 찾기 [ todoEntity ]
        Optional<TodoEntity> todoEntity = todoEntityRepository.findById(todoDto.getTno());
        // 2. Optional 객체에 엔티티 존재 여부 확인 [ 안전성 보장 ]
        if(todoEntity.isPresent()) {
            // 3. Optional 객체에 엔티티 꺼내기
            TodoEntity updateEntity = todoEntity.get();
            // 3. 엔티티 찾았으니 필드 수정
            updateEntity.setTstate(todoDto.isTstate());
        }

        return false;
    }

    public boolean doDelete(@RequestParam int tno) {

        // 1. tno[pk필드번호]에 해당하는 엔티티 삭제
        todoEntityRepository.deleteById(tno);
        return false;
    }
}
