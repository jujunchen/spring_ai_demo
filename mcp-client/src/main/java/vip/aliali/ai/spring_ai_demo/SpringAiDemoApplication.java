package vip.aliali.ai.spring_ai_demo;

import com.fasterxml.jackson.core.type.TypeReference;
import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import io.modelcontextprotocol.spec.McpClientTransport;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Function;

@SpringBootApplication
public class SpringAiDemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringAiDemoApplication.class, args);

		//编码方式调用
//		McpSyncClient mcpClient = McpClient.sync(HttpClientSseClientTransport.builder("http://localhost:8081").build()).build();
//
//		mcpClient.initialize();
//		McpSchema.ListToolsResult toolsList = mcpClient.listTools();
//
//		System.out.println(toolsList);
//
//		McpSchema.CallToolResult result = mcpClient.callTool(new McpSchema.CallToolRequest("getWeatherByCityName", Map.of("cityName", "杭州")));
//
//		System.out.println(result);
//
//		mcpClient.closeGracefully();
	}

}
