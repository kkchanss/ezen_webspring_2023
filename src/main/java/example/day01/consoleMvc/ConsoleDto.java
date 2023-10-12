package example.day01.consoleMvc;

import java.time.LocalDate;

public class ConsoleDto {

    private int no;
    private String title;
    private LocalDate dueDate;
    private boolean finished;

    public ConsoleDto() {}

    public ConsoleDto(int no, String title, LocalDate dueDate, boolean finished) {
        this.no = no;
        this.title = title;
        this.dueDate = dueDate;
        this.finished = finished;
    }

    public int getNo() {
        return no;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(LocalDate date) {
        this.dueDate = dueDate;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "ConsoleDto{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", dueDate=" + dueDate +
                ", finished=" + finished +
                '}' + "\n";
    }
}
