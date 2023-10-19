package example.task.object_relationship;

public class Main {
    public static void main(String[] args) {
        카테고리 카테1 = 카테고리.builder()
                .카테고리번호(1)
                .카테고리이름("카테1")
                .build();

        제품 제품1 = 제품.builder()
                .제품번호(1)
                .제품이름("제품1")
                .제품카테고리(카테1)
                .build();

        제품 제품2 = 제품.builder()
                .제품번호(2)
                .제품이름("제품2")
                .제품카테고리(카테1)
                .build();

        주문 주문1 = 주문.builder()
                .주문번호(1)
                .주문가격(3000)
                .주문제품(제품1)
                .build();

        주문 주문2 = 주문.builder()
                .주문번호(2)
                .주문가격(5000)
                .주문제품(제품2)
                .build();

        카테1.get참조제품객체들().add(제품1);
        카테1.get참조제품객체들().add(제품2);

        제품1.get참조주문객체들().add(주문1);
        제품2.get참조주문객체들().add(주문2);

        System.out.println("카테1의 제품1의 주문정보" + 카테1.get참조제품객체들().get(0));
        System.out.println("카테1의 제품2의 주문정보" + 카테1.get참조제품객체들().get(1));
    }
}
