package vip.aliali.ai.spring_ai_demo.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeTools {

    @Tool(description = "获取当前用户所在时区的时间")
    String getCurrentDateTime() {
        String now = LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
        System.out.println("Current time: " + now);
        return now;
    }

    @Tool( description = "根据传入的时间设置闹钟")
    void setAlarm(@ToolParam(description = "指定闹钟响的时间，格式示例:2007-12-03T10:15:30") String time) {
        System.out.println("Alarm set for " + time);
        LocalDateTime alarmTime = LocalDateTime.parse(time);
    }
}
