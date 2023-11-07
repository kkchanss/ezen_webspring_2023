package ezenweb.service;


import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.dto.PageDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardEntityRepository boardEntityRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberEntityRepository memberEntityRepository;
    @Transactional
    public boolean write(BoardDto boardDto) {
        // 1. FK 키의 엔티티를 찾는다
        // ========================== 단방향 =======================
            // [ FK로 사용할 PK를 알고 있어야 한다. 세션, 매개변수 가져오기 ]
            // 1. 예 ) 로그인된 회원의 pk번호 호출
                // ========== 로그인된 멤버 엔티티 찾기 ===============
        MemberDto loginDto = memberService.getMember();
        if(loginDto == null) { return false; }
            //memberService.getMember().getMno();
            // 2. 회원pk번호 가지고 pk엔티티 찾기
        Optional<MemberEntity> memberEntityOptional = memberEntityRepository.findById(loginDto.getMno());
            // 3. 유효성 검사 [ 로그읜이 안된상태 글쓰기 실패 ]
        if(!memberEntityOptional.isPresent()) { return false; }
                // ========== 로그인된 멤버 엔티티 찾기 end ===============
            // 4. 단방향 저장
        BoardEntity boardEntity =  boardEntityRepository.save(boardDto.saveToEntity());
        boardEntity.setMemberEntity(memberEntityOptional.get());
        // 양방향
        memberEntityOptional.get().getBoardEntityList().add(boardEntity);
        return boardEntity.getBno()>=1;
    }
    @Transactional
    public PageDto getAll(int page, String key, String keyword) { // + 검색기능

        // * JPA 페이징처리 라이브러리 지원
            // 1. Pageable : 페이지 인터페이스 ( 구현체 : 구현[추상메소드(인터페이스 가지는 함수)를 구현]해주는 객체)
                // 사용이유 : Repository인터페이스가 페이징처리할때 사용하는 인터페이스
            // 2. PageRequest : 페이지 구현체
                // of( 현재 페이지, 페이지별 게시물 수
                // 현재페이지 : 0부터 시작
                // 페이지별 게시물 수 : 만약에 2일때는 페이지마다 게시물 2개씩 출력
            // 3. Page : list와 마찬가지로 페이징결과의 여러개 객체를 저장하는 타입 [인터페이스]
                // list와 다르게 추가적으로 함수 지원
        Pageable pageable = PageRequest.of(page-1, 2);

        System.out.println("page : " + page);
        // 1. 모든 게시물 호출
        //Page<BoardEntity> boardEntities = boardEntityRepository.findAll(pageable);
        Page<BoardEntity> boardEntities = boardEntityRepository.findBySearch(key, keyword, pageable);
        List<BoardDto> boardDtos = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities) {
            boardDtos.add(boardEntity.saveToDto());
        }
            // 3. 총 페이지 수
        int totalPages = boardEntities.getTotalPages();
            // 4. 총 게시물 수
        long totalCount =  boardEntities.getTotalElements(); // 요소 : 성분/물체 다안위

        PageDto result = PageDto.builder().boardDtos(boardDtos).totalPages(totalPages).totalCount(totalCount).build();

        return result;
    }
    @Transactional
    public boolean update(BoardDto boardDto) {
        System.out.println("boardDto = " + boardDto);
        Optional<BoardEntity> optionalBoardEntity = boardEntityRepository.findById(boardDto.getBno());

        if(optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            boardEntity.setBtitle(boardDto.getBtitle());
            boardEntity.setBcontent(boardDto.getBcontent());

            //boardEntity.setBfile(boardDto.getBfile());
            return true;
        }

        return false;
    }
    @Transactional
    public boolean delete(int bno) {

        Optional<BoardEntity> optionalBoardEntity = boardEntityRepository.findById(bno);

        if(optionalBoardEntity.isPresent()) {
            boardEntityRepository.deleteById(bno);
            return true;
        }

        return false;
    }

    @Transactional
    public BoardDto getOne(int bno) {

        Optional<BoardEntity> optionalBoardEntity = boardEntityRepository.findById(bno);

        if(optionalBoardEntity.isPresent()) {
            return optionalBoardEntity.get().saveToDto();
        }
        return null;
    }
}
