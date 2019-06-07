package com.tfjybj.english.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author: 王雪芬
 * @Description: webSocket配置
 * @Date: Created in 9:58 2019/5/5
 */
@Configuration
public class WebSocketConfig {
    /***
     * 注入ServerEndpointExporter,只有这样才会自动注册@ServerEndpoint
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
