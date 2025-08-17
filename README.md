# spring_ai_demo

Spring AI 演示项目，用于测试和展示 Spring AI 与 Model Context Protocol (MCP) 的集成功能。

## 项目概述

本项目是一个基于 Spring AI 的演示项目，展示了如何将 Spring AI 与智谱AI模型集成，并通过 Model Context Protocol (MCP) 实现客户端与服务器之间的工具调用交互。

项目分为两个主要模块：
1. **mcp-client**: MCP 客户端，集成了智谱AI模型，可以调用AI功能和工具
2. **mcp-server**: MCP 服务器，提供自定义工具供客户端调用

## 功能特性

- 集成智谱AI大模型进行对话生成
- 实现基于 MCP 协议的客户端-服务器通信
- 提供自定义工具供AI调用，如日期时间工具、天气查询工具等
- 支持工具回调机制，实现AI与外部服务的交互

## 系统要求

- Java 21 或更高版本
- Maven 3.x
- 智谱AI API Key (需在环境变量中配置)

## 项目结构

```
spring_ai_demo/
├── mcp-client/           # MCP客户端模块
│   ├── src/main/java     # Java源代码
│   │   └── vip/aliali/ai/spring_ai_demo
│   │       ├── config/   # 配置类
│   │       ├── controller/ # 控制器类
│   │       └── tools/    # 自定义工具类
│   └── src/main/resources
│       └── application.properties # 客户端配置文件
├── mcp-server/           # MCP服务器模块
│   ├── src/main/java     # Java源代码
│   │   └── vip/aliali/ai/spring_ai_demo/mcp/server
│   │       └── WeatherService.java # 天气服务工具
│   └── src/main/resources
│       └── application.properties # 服务器配置文件
└── README.md
```

## 环境配置

1. 设置智谱AI API Key：
   ```bash
   # Windows (cmd)
   set ZHIPUAI_API_KEY=your_api_key_here
   
   # Windows (PowerShell)
   $env:ZHIPUAI_API_KEY="your_api_key_here"
   
   # Linux/macOS
   export ZHIPUAI_API_KEY=your_api_key_here
   ```

## 安装和运行

1. 克隆项目到本地：
   ```bash
   git clone <repository-url>
   cd spring_ai_demo
   ```

2. 构建项目：
   ```bash
   mvn clean install
   ```

3. 启动MCP服务器：
   ```bash
   cd mcp-server
   mvn spring-boot:run
   ```
   
   或者运行打包后的jar文件：
   ```bash
   mvn clean package
   java -jar target/spring-ai-mcp-server-demo-0.0.1-SNAPSHOT.jar
   ```

4. 启动MCP客户端：
   ```bash
   cd mcp-client
   mvn spring-boot:run
   ```
   
   或者运行打包后的jar文件：
   ```bash
   mvn clean package
   java -jar target/spring-ai-mcp-client-demo-0.0.1-SNAPSHOT.jar
   ```

## API 接口

### MCP客户端接口

1. **AI对话生成**
   ```
   POST /ai/generate?message={your_message}
   ```
   
2. **获取当前日期**
   ```
   GET /ai/get-date
   ```
   
3. **设置闹钟**
   ```
   GET /ai/set-alarm?time={time}
   ```

### MCP服务器接口

服务器运行在8081端口，提供天气查询工具供客户端调用。

## 使用示例

1. 获取当前日期：
   ```bash
   curl http://localhost:8080/ai/get-date
   ```

2. 进行AI对话：
   ```bash
   curl -X POST "http://localhost:8080/ai/generate?message=你好，介绍一下你自己"
   ```

3. 设置闹钟：
   ```bash
   curl "http://localhost:8080/ai/set-alarm?time=10分钟"
   ```

## 开发指南

### 添加自定义工具

1. 在 mcp-server 模块中创建新的服务类
2. 使用 `@Service` 注解标记服务类
3. 使用 `@Tool` 注解标记工具方法
4. 使用 `@ToolParam` 注解标记工具参数

### 配置日志级别

项目中已配置详细的日志输出，可以通过修改 [application.properties](file:///D:/projects/spring_ai_demo/mcp-client/src/main/resources/application.properties) 文件中的日志级别来控制输出详细程度。

## 贡献

欢迎提交 Issue 和 Pull Request 来改进这个演示项目。