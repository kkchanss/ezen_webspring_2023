package example.day06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Service 사용처 Controller
@Service
public class NoteService {

    @Autowired
    private NoteEntityRepository noteEntityRepository;

    @Transactional
    public boolean bWrite(@RequestBody NoteDto noteDto) {

        // 1. dto --> 엔티티 변경
        noteEntityRepository.save(noteDto.toEntity());
        return false;
    }

    @Transactional
    public List<NoteDto> bList() {

        // 1. 엔티티 -> dto 변경
        List<NoteDto> dtoList = new ArrayList<>();
        List<NoteEntity> list = noteEntityRepository.findAll();
        list.forEach(r ->{
            dtoList.add(r.toDto());
        });
        return dtoList;
    }

    // @Transactional : 해당 주입된 함수내에서 사용중인 SQL를 트랜잭션 단위로 적용
    @Transactional // 트랜잭션 : 하나/여럿 작업들을 묶어서 최소단위 업무처리
    public boolean bUpdate(@RequestBody NoteDto noteDto) {

        // 1. 수정할 pk번호에 해당하는 엔티티 찾기 ( 엔티티를 포장(null 방지)해서 반환)
        Optional<NoteEntity> optionalNoteEntity = noteEntityRepository.findById(noteDto.getNo());
        if(optionalNoteEntity.isPresent()) {
            NoteEntity noteEntity = optionalNoteEntity.get();
            noteEntity.setTitle(noteDto.getTitle());
            noteEntity.setWriter(noteDto.getWriter());
        }
        return false;
    }

    @Transactional
    public boolean bDelete(@RequestParam int no ) {

        noteEntityRepository.deleteById(no);
        return false;
    }
}
