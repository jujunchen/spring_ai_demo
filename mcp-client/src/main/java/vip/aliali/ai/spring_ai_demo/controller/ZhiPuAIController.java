package vip.aliali.ai.spring_ai_demo.controller;

import io.modelcontextprotocol.client.McpSyncClient;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.aliali.ai.spring_ai_demo.tools.DateTimeTools;

import java.util.List;
import java.util.Map;

@RestController
public class ZhiPuAIController {

    @Autowired
    private ZhiPuAiChatModel zhiPuAiChatModel;
    @Resource
    private ChatClient zhiPuAiClient;
    @Autowired
    private List<McpSyncClient> mcpSyncClients;

    @PostMapping("/ai/generate")
    public String generate(@RequestParam String message) {
        return zhiPuAiClient.prompt()
                .user(message)
                .call()
                .content();
    }

    /**
     * 调用工具获取当前日期
     */
    @GetMapping("/ai/get-date")
    public String getDate() {
        return zhiPuAiClient.prompt()
                .user("今天是几号")
                .tools(new DateTimeTools())
                .call()
                .content();
    }

    /**
     * 调用两个工具设置闹钟，首先获取当前时间，然后通过当前时间设置指定时间后的闹钟
     */
    @GetMapping("/ai/set-alarm")
    public String setAlarm(@RequestParam(value = "time") String time) {
        return zhiPuAiClient.prompt()
                .system("你是一个生活助手，能理解用户的意图，设置闹钟，你首先要获取到正确的用户当前时区时间，再根据用户的意图，设置闹钟")
                .user(time)
                .tools(new DateTimeTools())
                .call()
                .content();
    }

}
