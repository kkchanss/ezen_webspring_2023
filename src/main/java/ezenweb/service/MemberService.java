package ezenweb.service;

import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    // Controller -> Service -> Repository
    // Controller <- Service <- Repository
    @Autowired
    MemberEntityRepository memberEntityRepository;

    // 1. [C] 회원가입
    @Transactional // 트랜잭션 : 여러개 SQL를 하나의 최소 단위 [ 성공, 실패 ]
    public boolean postMember(MemberDto memberDto) {
        System.out.println("memberDto = " + memberDto);
        MemberEntity memberEntity = memberEntityRepository.save(memberDto.toEntity());
        return memberEntity.getMno() >= 1;
    }

    // 2. [R] 회원정보 호출
    @Transactional // 트랜잭션 : 여러개 SQL를 하나의 최소 단위 [ 성공, 실패 ]
    public MemberDto getMember(int mno) {
        // 1. mno[회원번호pk]를 이용한 회원 엔티티 찾기
        Optional<MemberEntity> entity = memberEntityRepository.findById(mno);
        // 2. optional클래스로 검색한 반환값 확인
        if(entity.isPresent()) { // 3. 만약에 optional 클래스 안에 엔티티가 들어가있으면
            // 4. optional 클래스에서 엔티티 꺼내기
            MemberEntity entity1 = entity.get();
            // 5. entity -> dto 변환해서 반환
            return entity1.toDto();
        }
        return null;
    }

    @Transactional
    // 3. [U] 회원정보 수정
    public boolean updateMember(MemberDto memberDto) {
        // 1. 수정할 엔티티 찾기 [ mno ]
        Optional<MemberEntity> entity = memberEntityRepository.findById(memberDto.getMno());
        // 2. optional 클래스로 검색한 반환값 확인
        if(entity.isPresent()) {
            // 3. 엔티티 꺼내기
            MemberEntity entity1 = entity.get();
            // 4. 엔티티 수정 [ 엔티티 객체를 수정하면 엔티티는 테이블과 매핑된 상태이므로 DB의 정보도 같이 수정 ]
            entity1.setMpassword(memberDto.getMpassword());
            entity1.setMphone(memberDto.getMphone());
            entity1.setMname(memberDto.getMname());
            // 5. 성공시
            return true;
        }
        return false;
    }
    // 4. [D] 회원탈퇴
    @Transactional // 트랜잭션 : 여러개 SQL를 하나의 최소 단위 [ 성공, 실패 ]
    public boolean deleteMember(int mno) {
        // 1. 삭제할 엔티티 찾기
        Optional<MemberEntity> optionalMemberEntity = memberEntityRepository.findById(mno);
        // 2. 만약에 삭제할 엔티티가 반환/검색 존재하면
        if(optionalMemberEntity.isPresent()) {
            memberEntityRepository.delete(optionalMemberEntity.get()); // 3. 엔티티 삭제
            return true;
        }
        return false;
    }

    // 아이디 찾기
    public String findId(MemberDto memberDto) {
        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();
        for (MemberEntity memberEntity : memberEntityList) {
            if (memberEntity.getMphone().equals(memberDto.getMphone())
                    && memberEntity.getMname().equals(memberDto.getMname())) {
                return memberEntity.getMemail();
            }
        }
        return null;
    }

    // 비밀번호 찾기
    public String findPw(MemberDto memberDto) {
        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();
        for (MemberEntity memberEntity : memberEntityList) {
            if (memberEntity.getMphone().equals(memberDto.getMphone())
                    && memberEntity.getMemail().equals(memberDto.getMemail())) {
                return memberEntity.getMpassword();
            }
        }
        return null;
    }

    // 로그인
    public MemberDto login(MemberDto memberDto) {
        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();
        for (MemberEntity memberEntity : memberEntityList) {
            if (memberEntity.getMpassword().equals(memberDto.getMpassword())
                    && memberEntity.getMemail().equals(memberDto.getMemail())) {

                return memberEntity.toDto();
            }
        }
        return null;
    }

}
