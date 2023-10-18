package ezenweb.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

// ------------------------ 1. 엔티티를 이용한 테이블 생성할 때 -------------------
@Entity // 해당 클래스를 엔티티로 사용
@Table(name = "board") // 테이블명 설정
public class BoardEntity { // 테이블 설계

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 필드 설계
    private int bno;
    private String btitle;
    private String bcontent;
    private int bview;
    private LocalDateTime bdate; // baseTime 클래스로부터 상속받으면 자동...
    // [ BaseTime 클래스가 상속해주는 필드 : 1. 작성일 2. 수정일 ]
}

/*
    ----------------------- 2. 직접 DDL 작성헤서 테이블 생성할 때 -----------------

 */
