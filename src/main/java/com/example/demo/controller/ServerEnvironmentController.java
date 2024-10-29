package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ServerEnvironmentController {

    @GetMapping("/server-environment")
    public Map<String, String> getServerEnvironmentInfo() {
        Map<String, String> envInfo = new HashMap<>();
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] macArray = network.getHardwareAddress();
            StringBuilder macAddress = new StringBuilder();
            for (int i = 0; i < macArray.length; i++) {
                macAddress.append(String.format("%02X%s", macArray[i], (i < macArray.length - 1) ? "-" : ""));
            }

            envInfo.put("OS Name", System.getProperty("os.name"));
            envInfo.put("OS Version", System.getProperty("os.version"));
            envInfo.put("Java Version", System.getProperty("java.version"));
            envInfo.put("User Name", System.getProperty("user.name"));
            envInfo.put("User Home", System.getProperty("user.home"));
            envInfo.put("User Directory", System.getProperty("user.dir"));
            envInfo.put("IP Address", ip.getHostAddress());
            envInfo.put("MAC Address", macAddress.toString());
        } catch (Exception e) {
            envInfo.put("Error", "Unable to retrieve server environment information");
        }
        return envInfo;
    }
}
