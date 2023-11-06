package ezenweb.service;


import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<BoardDto> getAll() {

        List<BoardEntity> list = boardEntityRepository.findAll();
        List<BoardDto> result = new ArrayList<>();

        for (BoardEntity boardEntity : list) {
            result.add(boardEntity.saveToDto());
        }

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
