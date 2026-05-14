package com.agrupa.tat_3ds.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.DatagramSocket;
import java.net.InetAddress;

@RestController
@RequestMapping("/trabalho")
public class RedeController {

    @GetMapping("/config/rede")
    public ResponseEntity<String> obterIp() {
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            String ip = socket.getLocalAddress().getHostAddress();
            return ResponseEntity.ok(ip);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao obter IP");
        }
    }
}
