package vip.aliali.ai.rag.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZhiPuAIController {

    @Autowired
    private ZhiPuAiChatModel zhiPuAiChatModel;
    @Resource
    private ChatClient zhiPuAiClient;

    @PostMapping("/ai/generate")
    public String generate(String msg) {
        return zhiPuAiClient.prompt()
                .user(msg)
                .call()
                .content();
    }


}
