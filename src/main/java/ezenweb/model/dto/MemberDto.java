package ezenweb.model.dto;

import ezenweb.model.entity.MemberEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@NoArgsConstructor @AllArgsConstructor
@Builder @ToString
@Getter @Setter
public class MemberDto implements UserDetails, OAuth2User {
    private int mno;            // 1. 회원번호
    private String memail;      // 2. 이메일 [ 회원아이디 객체 ]
    private String mpassword;   // 3. 비밀번호
    private String mname;       // 4. 이름
    private String mphone;      // 5. 연락처
    private String mrole;       // 6. 회원 등급 ( 일반 회원 = user, 관리자 회원 = admin )

    // + baseTime
    private LocalDateTime cdate;
    private LocalDateTime udate;


    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .mno(this.mno)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mrole(this.mrole)
                .mphone(this.mphone)
                .mname(this.mname)
                .build();
    }

    // -------------------------------- OAuth2User -------------------------------- //

    private Map<String, Object> 소셜회원정보;

    @Override // oauth2 회원의 아이디
    public String getName() {
        return memail;
    }

    @Override // oauth2 회원의 정보
    public Map<String, Object> getAttributes() {
        return 소셜회원정보;
    }

    // -------------------------------- UserDetails -------------------------------- //

    // Collection : 컬렉션 프레임워크 : set, list, map
    List<GrantedAuthority> 권한목록;



    @Override // 계정 권한 목록
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return 권한목록;
    }

    @Override // 계정 비밀번호
    public String getPassword() {
        return mpassword;
    }

    @Override // 계정 아이디
    public String getUsername() {
        return memail;
    }

    @Override // 계정 만료 여부
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override // 계정 잠금 여부
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override // 계정 증명 여부
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // 계정 활성화 여부
    public boolean isEnabled() {
        return true;
    }


}
