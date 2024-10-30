package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.HashMap;
import java.util.Map;

@RestController // 이 클래스는 RESTful 웹 서비스의 컨트롤러임을 나타냅니다.
public class ServerEnvironmentController {

    @GetMapping("/server-environment") // 이 메서드는 "/server-environment" 경로로 GET 요청을 처리합니다.
    public Map<String, String> getServerEnvironmentInfo() {
        Map<String, String> envInfo = new HashMap<>(); // 서버 환경 정보를 저장할 맵을 생성합니다.
        try {
            InetAddress ip = InetAddress.getLocalHost(); // 로컬 호스트의 IP 주소를 가져옵니다.
            NetworkInterface network = NetworkInterface.getByInetAddress(ip); // IP 주소에 해당하는 네트워크 인터페이스를 가져옵니다.
            byte[] macArray = network.getHardwareAddress(); // 네트워크 인터페이스의 MAC 주소를 가져옵니다.
            StringBuilder macAddress = new StringBuilder(); // MAC 주소를 저장할 StringBuilder 객체를 생성합니다.
            for (int i = 0; i < macArray.length; i++) { // MAC 주소의 각 바이트를 반복합니다.
                macAddress.append(String.format("%02X%s", macArray[i], (i < macArray.length - 1) ? "-" : "")); // 각 바이트를 16진수 문자열로 변환하여 MAC 주소 문자열에 추가합니다.
            }

            envInfo.put("OS Name", System.getProperty("os.name")); // 운영체제 이름을 맵에 추가합니다.
            envInfo.put("OS Version", System.getProperty("os.version")); // 운영체제 버전을 맵에 추가합니다.
            envInfo.put("Java Version", System.getProperty("java.version")); // 자바 버전을 맵에 추가합니다.
            envInfo.put("User Name", System.getProperty("user.name")); // 사용자 이름을 맵에 추가합니다.
            envInfo.put("User Home", System.getProperty("user.home")); // 사용자 홈 디렉토리를 맵에 추가합니다.
            envInfo.put("User Directory", System.getProperty("user.dir")); // 현재 작업 디렉토리를 맵에 추가합니다.
            envInfo.put("IP Address", ip.getHostAddress()); // IP 주소를 맵에 추가합니다.
            envInfo.put("MAC Address", macAddress.toString()); // MAC 주소를 맵에 추가합니다.
        } catch (Exception e) { // 예외가 발생하면
            envInfo.put("Error", "Unable to retrieve server environment information"); // 오류 메시지를 맵에 추가합니다.
        }
        return envInfo; // 서버 환경 정보를 담은 맵을 반환합니다.
    }
}