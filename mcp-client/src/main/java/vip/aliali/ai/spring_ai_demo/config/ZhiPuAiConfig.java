package vip.aliali.ai.spring_ai_demo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.aliali.ai.spring_ai_demo.tools.DateTimeTools;

@Configuration
public class ZhiPuAiConfig {

    @Bean
    public ChatClient zhiPuAiClient(ZhiPuAiChatModel zhiPuAiChatModel, ChatMemory chatMemory, ToolCallbackProvider tools) {
        return ChatClient.builder(zhiPuAiChatModel)
                .defaultToolCallbacks(tools) //把mcp server的工具加到这里
                .defaultTools(new DateTimeTools()) //client 自身的工具
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build(),
                        SimpleLoggerAdvisor.builder().build()

                )
                .defaultSystem("你叫小智，是一个聪明能干的生活小助手")
                .build();
    }
}
