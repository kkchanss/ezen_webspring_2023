package day04;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component // 해당 클래스를 스프링 컨테이너에 빈 등록
public class TodoDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public TodoDao() {
        // SQL 연동

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb","root","1234");
            System.out.println("성공");
        }catch (Exception e)
        {
            System.out.println("e = " + e);
        }
    }

    // 1.
    public boolean doPost(TodoDto todoDto) {

        String sql = "insert into todo(tcontent, tstate) value(?,?)";
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, todoDto.getTcontent());
            ps.setBoolean(1, todoDto.isTstate());
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    // 2.
    public List<TodoDto> doGet() {

        List<TodoDto> list = new ArrayList<>();
        String sql = "select * from todo";

        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                // 생성자 대신에 빌더패턴을 이용한 객체 생성 후 리스트에 저장
                list.add(TodoDto.builder()
                        .tno(rs.getInt("tno"))
                        .tcontent(rs.getString("tcontent"))
                        .tstate(rs.getBoolean("tstate"))
                        .build()

                );
            }
        }catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // 3.
    public boolean doPut(TodoDto todoDto) {

        String sql = "update todo set tstate = ? where tno = ?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, todoDto.isTstate());
            ps.setInt(2, todoDto.getTno());
            int count = ps.executeUpdate();
            if(count == 1) return true;
        }catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }

    // 4.
    public boolean doDelete(int tno) {
        String sql = "delete from todo where tno = ?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1,tno);
            int count = ps.executeUpdate();
            if(count == 1) return true;
        }catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
