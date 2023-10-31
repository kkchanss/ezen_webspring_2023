package ezenweb.model.repository;

import ezenweb.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity, Integer> {
    // 인터페이스 추상 메소드
    // - 이메일 이용한 엔티티/레코드 검색 select * from member wherer memail = 변수
    // 반환자료형 추상메소드 (매개변수)
    // 1. 필드명을 이용한 엔티티 검색 : 반환엔티티 findBy필드명(매개변수);
        // 1. 동일한 이메일 있을때 엔티티 반환 없으면 NULL 반환
    // MemberEntity findByMemail(String memail);
        // 2. 동일한 이메일 있을때 'Optional' 반환 없으면 'Optional' 반환
    MemberEntity findByMemail(String memail);
    //Optional<MemberEntity> findByMemail(String memail);
    Optional<MemberEntity> findByMemailAndMphone(String memail, String mphone);
    Optional<MemberEntity> findByMnameAndMphone(String mname,String mphone);
    Optional<MemberEntity> findByMemailAndMpassword(String memail,String mpwd);
        // 3. 동일한 이메일 있을때 true
    boolean existsByMemail(String memail);

        // 4. 조건에 and/or 있을때 이메일과 이름이 같을때
        // select * from member where mname = 변수 and memail = 변수;
    MemberEntity findByMnameAndMemail(String name, String memail);


}
