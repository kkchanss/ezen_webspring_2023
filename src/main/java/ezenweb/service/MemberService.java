package ezenweb.service;

import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class MemberService implements
        UserDetailsService, // 일반회원 서비스 : loadUserByUsername 메소드 구현
        OAuth2UserService<OAuth2UserRequest, OAuth2User> { // Oauth2 회원 서비스 : loadUser 메소드 구현 [oauth2 로그인된 회원정보를 받는 메소드]

    @Autowired
    MemberEntityRepository memberEntityRepository;

    // ---------------------------------------- 2. Oauth2 회원 ------------------------------------- //

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 1. 로그인을 성공한 oauth2 계정의 정보 호출
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);
        System.out.println("oAuth2User = " + oAuth2User);

        // 2. 인증결과 (카카오, 네이버, 구글)
        // 2-1 인증한 소셜 서비스 아이디 (각 회사명) 찾기
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        System.out.println("registrationId = " + registrationId);

        String memail = null; String mname = null;

        // 2-2 카카오이면
        if(registrationId.equals("kakao")) {
            // oAuth2User : Name : [3142825703], Granted Authorities: [[ROLE_USER, SCOPE_account_email, SCOPE_profile_nickname]], User Attributes: [{id=3142825703, connected_at=2023-11-01T03:22:57Z, properties={nickname=찬희}, kakao_account={profile_nickname_needs_agreement=false, profile_image_needs_agreement=true, profile={nickname=찬희}, has_email=true, email_needs_agreement=false, is_email_valid=true, is_email_verified=true, email=rlacksgml99@naver.com, has_gender=true, gender_needs_agreement=true}}]
            System.out.println("kakao_account : " + oAuth2User.getAttribute("kakao_account"));

            Map<String, Object> kakao_account = (Map<String, Object>)oAuth2User.getAttributes().get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>)kakao_account.get("profile");

            memail = kakao_account.get("email").toString();
            mname = profile.get("nickname").toString();

            System.out.println("mname = " + mname);
            System.out.println("memail = " + memail);
        }
        // 2-2 네이버이면
        if(registrationId.equals("naver")) {
            // oAuth2User = Name: [{id=pyniTXs2QmQgi95Me6E7oSejPEL5fFAwjN9miDZj32c, nickname=Java, email=rlacksgml99@naver.com}], Granted Authorities: [[ROLE_USER]], User Attributes: [{resultcode=00, message=success, response={id=pyniTXs2QmQgi95Me6E7oSejPEL5fFAwjN9miDZj32c, nickname=Java, email=rlacksgml99@naver.com}}]
            Map<String, Object> response = (Map<String, Object>)oAuth2User.getAttributes().get("response");
            memail = response.get("email").toString();
            mname = response.get("nickname").toString();
            System.out.println("mname = " + mname);
            System.out.println("memail = " + memail);
        }
        // 2-2 구글이면
        if(registrationId.equals("google")) {
            // oAuth2User = Name: [103299139840618459579], Granted Authorities: [[ROLE_USER, SCOPE_https://www.googleapis.com/auth/userinfo.email, SCOPE_https://www.googleapis.com/auth/userinfo.profile, SCOPE_openid]], User Attributes: [{sub=103299139840618459579, name=김찬희, given_name=찬희, family_name=김, picture=https://lh3.googleusercontent.com/a/ACg8ocKVe_WZs4iKOXmnfDEZ_GU5X6ScgSajnooNMQDWau4f=s96-c, email=rlacksgml99@gmail.com, email_verified=true, locale=ko}]
            memail = oAuth2User.getAttributes().get("email").toString();
            mname = oAuth2User.getAttributes().get("name").toString();
        }

        // 3: 일반회원(UseDetails) + OAUTH2(OAuth2User) 통합회원 = DTO 같이 쓰기
            // 3-1 권한 목록에 추가
        List<GrantedAuthority> 권한목록 = new ArrayList<GrantedAuthority>();
        권한목록.add(new SimpleGrantedAuthority("ROLE_" + registrationId));
            // 3-2 DTO 만들기
        MemberDto memberDto = MemberDto.builder()
                // oauth2는 패스워드를 알 수 없다..
                .memail(memail)
                .mname(mname)
                .권한목록(권한목록)
                .소셜회원정보(oAuth2User.getAttributes())
                .build();
            // 3-3 DB 처리
                // 만약 처음 접속한 OAUTH2 회원이면 DB처리
                // 아니면 DB 처리 x
        if(!memberEntityRepository.existsByMemail(memberDto.getMemail())) { // 해당 이메일이 db에 없으면
            memberDto.setMrole("ROLE_USER");
            memberDto.setMpassword(new BCryptPasswordEncoder().encode(mname)); // db null 피하기 위해
            // 난수
            Random random = new Random();
            int front = random.nextInt(999);
            int middle = random.nextInt(9999);
            int back = random.nextInt(9999);
            memberDto.setMphone(front + "-" + middle + "-" + back);
            memberEntityRepository.save(memberDto.toEntity());
        }else{ // 만약에 처음 접속이 아니면 기존 권한을 db에서 가져와서 넣어주기 !
            memberDto.setMrole(memberEntityRepository.findByMemail(memail).getMrole());
        }
        // 권한 추가
        memberDto.get권한목록().add(new SimpleGrantedAuthority(memberDto.getMrole()));
        return memberDto;
    }

    // ---------------------------------------- 1. 일반 회원 ------------------------------------- //
        // p. 687
        // 1. UserDetailsService 구현체
        // 2. 인증처리 해주는 메소드 구현 [loadUserByUsername]
        // 3. loadUserByUsername 메소드는 무조건(꼭) UserDetails객체를 반환해야 한다.
        // 4. UserDetails 객체를 이용한 패스워드 검증과 사용자 권한을 확인 하는 동작(메소드)


    // @Autowired : 사용불가 (스프링 컨테이너에 등록 안된 빈(객체)이므로 불가능)
    private PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    // 9. 시큐리티 사용시 인증정보[로그인상태] 호출
    @Transactional
    public MemberDto getMember() {
        // ! : 시큐리티 사용하기 전에는
        // 시큐리티 사용할때는 일단 서블릿 세션이 아니고 시큐리티 저장소 이용.
        System.out.println("시큐리티에 저장된 세션 정보 저장소 : "
                + SecurityContextHolder.getContext());
        System.out.println("시큐리티에 저장된 세션 정보 저장소 저장된 인증 : "
                + SecurityContextHolder.getContext().getAuthentication());
        System.out.println("시큐리티에 저장된 세션 정보 저장소 저장된 인증 호출 : "
                + SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        // * 인증에 성공한 정보 호출 [ 세션 호출 ]
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(o.toString());
        // 1. 만약에 인증 결과가 실패했을때/없을때
        if(o.equals("anonymousUser")) { return null; } // 로그인 안해썽 ..
        // 2. 인증결과에 저장된 UserDetails로 타입 변환
        UserDetails userDetails = (UserDetails)o;
        MemberEntity memberEntity = mr.findByMemail(userDetails.getUsername());
        return MemberDto.builder().memail(userDetails.getUsername()).mno(memberEntity.getMno()).build();
    }

    // 8.
    @Override
    public UserDetails loadUserByUsername(String memail) throws UsernameNotFoundException {
        // * login페이지에서 form을 통해 전송된 아이디 받고 (패스워드 없음)
        System.out.println("loadUserByUsername username = " + memail);

        // - , p.684 인증 절차
        // 1. 사용자의 아이디만으로 사용자 정보를 로딩 [ 불러오기 ] - p. 728
        MemberEntity memberEntity = mr.findByMemail(memail);
            // 2. 없는 아이디이면
        // throw : 예외처리 던지기 // new UsernameNotFoundException() : username 없을때 사용하는 예외클래스
        if(memberEntity == null) { throw new UsernameNotFoundException("없는 아이디입니다."); }
        // 2. 로딩[불러오기]된 사용자의 정보를 이용해서 패스워드를 검증
/*            // 2-1 있는 아이디이면
        UserDetails userDetails = User.builder()
                .username(memberEntity.getMemail()) // 찾은 사용자 정보의 아이디
                .password(memberEntity.getMpassword()) // 찾은 사용자의 정보 내 패스워드
                .authorities(memberEntity.getMrole()).build(); // 찾은 사용자 정보의 권한*/
        // 2-1 권한 목록에 추가
        List<GrantedAuthority> 권한목록 = new ArrayList<>();
        권한목록.add(new SimpleGrantedAuthority(memberEntity.getMrole()));

        // 2-2 DTO 만들기
        MemberDto memberDto = MemberDto.builder()
                .memail(memberEntity.getMemail())
                .mpassword(memberEntity.getMpassword())
                .mname(memberEntity.getMname())
                .권한목록(권한목록)
                .build();



        return memberDto;
    }
    // ------------------------------------------------ //

    //  service < - repository
    //  service <- repository
    @Autowired
    MemberEntityRepository mr;

    @Autowired
    HttpServletRequest request;

    // 1. 회원가입
    @Transactional
    public boolean postMember(MemberDto mdt)
    {
        // --------- 암호화 --------- //
            // - 입력받은 비밀번호[memberDto.getMpassword()]를 암호화 해서 다시
        mdt.setMpassword(passwordEncoder.encode(mdt.getMpassword()));
            // mdt.getMpassword() -> passwordEncoder.encode() -> mdt.setMpassword()
        System.out.println("mdt.getMpassword = " + mdt.getMpassword());
        // -------------------- //
        MemberEntity result = mr.save(mdt.toEntity());
        return result.getMno() >=1;
    }
   /* //2 r
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
    }*/

/*    // ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★로그인 세션 있는거
    @Transactional
    public MemberDto doGet(){
        // 세션 호출
        Object session = request.getSession().getAttribute("loginDto");
        // 세션 검증
        if(session != null){
            return (MemberDto)session;
        }

        return null;
    }*/

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
            //logout();
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
    @Transactional // 비밀번호 찾기
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
    /*@Transactional //6. 로그아웃
    public boolean logout()
    {

        if(null != req.getSession().getAttribute("loginDto"))
        {
            req.getSession().setAttribute("loginDto",null);
            return true;
        }
        return false;
    }*/

    // 이메일 중복검사 
    @Transactional
    public boolean getFindMemail(String memail)
    {
        // 1. 이메일을 이용한 엔티티 찾기
        return mr.existsByMemail(memail);
    }

}
