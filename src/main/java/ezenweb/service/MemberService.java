package ezenweb.service;

import example.day06.NoteDto;
import example.day06.NoteEntity;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    //  service < - repository
    //  service <- repository
    @Autowired
    MemberEntityRepository mr;

    @Transactional
    public boolean postMember(MemberDto mdt)
    {

        System.out.println("mdt = " + mdt);

        MemberEntity result = mr.save(mdt.toEntity());
        return result.getMno() >=1;
    }
    //2 r
    @Transactional
    public MemberDto getMember(int mno)
    {
        System.out.println("mno = " + mno);
        Optional<MemberEntity> res = mr.findById(mno);
        MemberDto result = new MemberDto();
        if(res.isPresent())
        {
            result = res.get().toDto();
        }else{
            return null;
        }
        return result;
    }
    //3 u
    @Transactional
    public boolean updateMember(MemberDto mdt)
    {
        System.out.println("mdt = " + mdt);
        Optional<MemberEntity> memberEntityOptional =  mr.findById(mdt.getMno());
        if(memberEntityOptional.isPresent()) {
            MemberEntity _m = memberEntityOptional.get();
            _m.setMemail(mdt.getMemail());
            _m.setMphone(mdt.getMphone());
            _m.setMpassword(mdt.getMpassword());
            return true;
        }else{
            return false;}
    }
    //4 d
    @Transactional
    public boolean deleteMember(int mno)
    {
        System.out.println("mno = " + mno);
        if(mr.findById(mno).isPresent())
        {
            mr.deleteById(mno);
            return true;
        }else{
            return false;
        }
    }
    //아이디 찾기
    @Transactional
    public String findId(MemberDto _dto)
    {

        System.out.println("MemberService.doFindMemberId");
        System.out.println("_dto = " + _dto);
        Optional<MemberEntity> _m = mr.findByMnameAndMphone(_dto.getMname(), _dto.getMphone());
        if(_m.isPresent())
        {
            return _m.get().getMemail();
        }
        return null;
    }
    @Transactional
    public String findPw(MemberDto _dto)
    {
        System.out.println("_m.toString() = " +_dto.toString());
        Optional<MemberEntity> _m = mr.findByMemailAndMphone(_dto.getMemail(),_dto.getMphone());
        System.out.println("_m = " + _m.toString());
        if(_m.isPresent())
        {

            return _m.get().toDto().getMpassword();
        }
        return null;
    }

    @Autowired
    HttpServletRequest req;

    @Transactional
    public boolean login(MemberDto _dto)
    {
        if(null == req.getSession().getAttribute("loginDto"))
        {
            Optional<MemberEntity> optionalMemberEntity = mr.findByMemailAndMpassword(_dto.getMemail(),_dto.getMpassword());
            if(optionalMemberEntity.isPresent())
            {
                req.getSession().setAttribute("loginDto", optionalMemberEntity.get().toDto());
                return true;
            }
        }
        return false;
    }
    @Transactional
    public boolean logout()
    {

        if(null != req.getSession().getAttribute("loginDto"))
        {
            req.getSession().setAttribute("loginDto",null);
            return true;
        }
        return false;
    }
}
