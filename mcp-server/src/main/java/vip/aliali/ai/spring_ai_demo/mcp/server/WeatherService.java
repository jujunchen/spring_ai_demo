package vip.aliali.ai.spring_ai_demo.mcp.server;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    @Tool(description = "根据城市名称查询天气")
    public String getWeatherByCityName(
            @ToolParam(description = "城市名称") String cityName
    ) {
        System.out.println("getWeatherByCityName called with cityName: " + cityName);
       return cityName + "天气是晴天";
    }

}
