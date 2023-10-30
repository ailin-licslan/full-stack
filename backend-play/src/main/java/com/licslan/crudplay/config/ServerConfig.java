package com.licslan.crudplay.config;



import lombok.Getter;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @className: ServerConfig
 **/
@Getter
@Component
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {
    private Integer port;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        this.port = webServerInitializedEvent.getWebServer().getPort();
    }
}
