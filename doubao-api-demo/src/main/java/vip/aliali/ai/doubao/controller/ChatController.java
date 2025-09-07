package vip.aliali.ai.doubao.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Resource
    private ChatClient doubaoAIClient;

    @PostMapping("/ai/generate")
    public String generate(@RequestParam String message) {
        return doubaoAIClient.prompt()
                .user(message)
                .call()
                .content();
    }


}
