package ezenweb.model.repository;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardEntityRepository extends JpaRepository<BoardEntity, Integer> {

    // 추상메소드 이용한 엔티티 검색
    // - 기본 제공하는 함수 find~~, delete~~, save~~
    // 1. 해당 하는 제목의 엔티티 찾기
    //BoardEntity findByBtitle(String btitle);
    //Optional<BoardEntity> findByBtitle(String btitle);
    boolean existsByBtitle(String btitle);
    BoardEntity findByBtitle(String btitle);
    // + 페이징처리
    Page<BoardEntity> findByBtitle(String btitle, Pageable pageable);
    // + mysql 실제 SQL 작성하기 @Query
        // nativeQuery = true 사용하면 실제 mysql에서 사용하는 sql 표현을 사용
        // nativeQuery = false 이면 실제 sql아닌 jpql [ java + mysql ] 표현식 사용
        // SQL 안에서 매개변수를 표현할때  :매개변수명 // DAO에서는?
    //@Query(value = "select * from board", nativeQuery = true) // == findAll
    //@Query(value = "select * from board where bno = :bno", nativeQuery = true)
    //@Query(value = "select * from board where btitle = :btitle", nativeQuery = true)
    @Query(value = "select * from board where btitle like %:keyword%", nativeQuery = true) // 제목이 포함됨
    Page<BoardEntity> findBySearch(@Param("key")String key,@Param("keyword")String keyword, Pageable pageable);
}
