package example.day06;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository 사용처는 Service
@Repository // 스프링 컨테이너에 등록 [ 다른곳에 객체를 써야 돼서 (@Autowired) ]
public interface NoteEntityRepository extends JpaRepository<NoteEntity, Integer> {
}
