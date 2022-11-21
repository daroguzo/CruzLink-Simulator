package kr.co.direa.cruzlinksimulator.service;

import kr.co.direa.cruzlinksimulator.constant.MessageType;
import kr.co.direa.cruzlinksimulator.constant.Role;
import kr.co.direa.cruzlinksimulator.dto.Test;
import kr.co.direa.cruzlinksimulator.netty.tcp.TcpClient;
import kr.co.direa.cruzlinksimulator.netty.tcp.TcpServer;
import org.springframework.stereotype.Service;

@Service
public class SimulService {

    public void start(Test test) throws Exception {

        // FIXED 요청 테스트
        if (test.getRole().equals(Role.CLIENT) && test.getMessageType().equals(MessageType.FIXED)) {
            TcpClient tcpClient = new TcpClient(test);
            tcpClient.run();
        }

        // FIXED 응답 테스트
        if (test.getRole().equals(Role.SERVER) && test.getMessageType().equals(MessageType.FIXED)) {
            TcpServer tcpServer = new TcpServer(test);
            tcpServer.run();
        }

        // Json 요청 테스트
        if (test.getRole().equals(Role.CLIENT) && test.getMessageType().equals(MessageType.JSON)) {

        }

        // Json 응답 테스트
        if (test.getRole().equals(Role.SERVER) && test.getMessageType().equals(MessageType.JSON)) {

        }
        // XML 요청 테스트
        if (test.getRole().equals(Role.CLIENT) && test.getMessageType().equals(MessageType.XML)) {

        }

        // XML 응답 테스트
        if (test.getRole().equals(Role.SERVER) && test.getMessageType().equals(MessageType.XML)) {

        }
    }
}
