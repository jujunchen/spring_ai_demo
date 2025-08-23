package vip.aliali.ai.rag.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZhiPuAiConfig {

    @Bean
    public ChatClient zhiPuAiClient(ZhiPuAiChatModel zhiPuAiChatModel, ChatMemory chatMemory, VectorStore vectorStore) {
        PromptTemplate customPromptTemplate = PromptTemplate.builder()
                .renderer(StTemplateRenderer.builder().startDelimiterToken('<').endDelimiterToken('>').build())
                .template("""
                        <query>
            
                        上下文信息如下
            
                        ---------------------
                        <question_answer_context>
                        ---------------------
            
                        根据提供的背景信息（且不借助任何先验知识），请回答该查询
            
                        按照如下规则:
            
                        1. 如果答案不在上下文中，就说你不知道.
                        2. 避免诸如“基于上下文...”或“提供的信息...”.
                        """)
                .build();

        return ChatClient.builder(zhiPuAiChatModel)
                .defaultAdvisors(
                        QuestionAnswerAdvisor.builder(vectorStore).promptTemplate(customPromptTemplate).searchRequest(SearchRequest.builder().similarityThreshold(0.5d).topK(1).build()).build(),
                        MessageChatMemoryAdvisor.builder(chatMemory).build(),
                        SimpleLoggerAdvisor.builder().build()

                )
                .defaultSystem("你叫小智，是一个聪明能干的生活小助手")
                .build();
    }
}
