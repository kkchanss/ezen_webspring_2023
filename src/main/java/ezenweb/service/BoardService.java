package ezenweb.service;


import ezenweb.model.dto.BoardDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.repository.BoardEntityRepository;
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

    @Transactional
    public boolean write(BoardDto boardDto) {
        BoardEntity result =  boardEntityRepository.save(boardDto.saveToEntity());
        return result.getBno()>=1;
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

        Optional<BoardEntity> optionalBoardEntity = boardEntityRepository.findById(boardDto.getBno());

        if(optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            boardEntity.setBtitle(boardDto.getBtitle());
            boardEntity.setBcontent(boardDto.getBcontent());
            boardEntity.setBfile(boardDto.getBfile());
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
}
