package vip.aliali.ai.spring_ai_demo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZhiPuAiConfig {

    @Bean
    public ChatClient zhiPuAiClient(ZhiPuAiChatModel zhiPuAiChatModel, ChatMemory chatMemory) {
        return ChatClient.builder(zhiPuAiChatModel)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build(),
                        SimpleLoggerAdvisor.builder().build()

                )
                .defaultSystem("你叫小智，是一个聪明能干的生活小助手")
                .build();
    }
}
