package day01.consoleMvc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ConsoleDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public ConsoleDao() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb",
                    "root", "1234");
            System.out.println("DB sucess");
        }catch(Exception e) {
            System.out.println("DB error : " + e);
        }
    }
    public List<ConsoleDto> doGet() {
        List<ConsoleDto> list = new ArrayList<>();
        try{
            String sql = "select * from todo";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                ConsoleDto dto = new ConsoleDto();
                rs.getInt(1);
                rs.getString(2);
                LocalDate.parse(rs.getString(3));
                rs.getBoolean(4);
                list.add(dto);
            }

        }catch(Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public boolean doPost(ConsoleDto dto) {

        try{
            String sql = "insert into todo(title, dueDate, finished) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getTitle());
            ps.setString(2, dto.getDueDate().toString());
            ps.setBoolean(3, dto.isFinished());
            ps.execute();
            return true;
        }catch(Exception e) {
            System.out.println(e);
        }

        return false;
    }
}
