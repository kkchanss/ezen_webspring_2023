package ezenweb.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component // 스프링 컨테이너에 빈 등록
public class ChattingController extends TextWebSocketHandler {

    // 0. 서버소켓과 연동된 클라이언트소켓들을 저장하는 리스트
    private static List<WebSocketSession> 접속명단 = new ArrayList<>();

    @Override // 1. 클라이언트 소켓과 연동 성공했을때
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // * 접속 연동 성공 클라이언트소켓 명단 저장
        접속명단.add(session);
        System.out.println("session = " + session);
    }

    @Override // 2. 클라이언트 소켓과 연동 오류가 발생했을때
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("session = " + session + ", exception = " + exception);
    }

    @Override // 3. 클라이언트 소켓과 연동이 끊겼을때
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        접속명단.remove(session);
        System.out.println("session = " + session + ", status = " + status);
    }

    @Override // 4. 클라이언트 소켓으로부터 메세지를 받았을때
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for(WebSocketSession 세션 : 접속명단) {
            세션.sendMessage(message);
        }
        System.out.println("session = " + session + ", message = " + message);
    }
}
