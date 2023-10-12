package example.day01.consoleMvc;

import java.util.List;
import java.util.Scanner;

public class ConsoleStart {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        while(true) {
            doGet();
            doPost();
        }

    }// m end


    public static void doGet() {
        ConsoleController controller = new ConsoleController();
        List<ConsoleDto> result = controller.doGet();
    }

    public static void doPost() {
        System.out.println("title : "); String title = sc.next();
        ConsoleController controller = new ConsoleController();
        boolean result = controller.doPost(title);
        System.out.println(result);
    }


}

/*
// syso : 이클립스 자동완성
// sout
        System.out.println("sout -> println");
                System.out.printf("souf -> printf");
                System.out.println("consoleStart.main"); // soutm : 현재 실행중인 함수명 호출
                System.out.println("args = " + Arrays.toString(args)); // soutm 현재 실행주인 함수의 매개변수 호출
                System.out.println("args = " + args); // soutv 변수 출력
// ctrl + shift + f10 또는 왼쪽에 실행 화살표*/
