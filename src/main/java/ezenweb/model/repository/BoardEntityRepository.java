package ezenweb.model.repository;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardEntityRepository extends JpaRepository<BoardEntity, Integer> {

}
