//declare where the file lives
package com.collabspace.CollabSpace.config;

//import things
//1. import the handler
import com.collabspace.CollabSpace.websocket.CollabWebSocketHandler;
//2.import the one who allows using the instance created for CollabWebSocketHandler
import org.springframework.beans.factory.annotation.Autowired;
//3.import the annotation which tells the spring boot the class below is configuration class
import org.springframework.context.annotation.Configuration;
//4.import the one which enables support for websocket in springboot as by default it is disabled
import org.springframework.web.socket.config.annotation.EnableWebSocket;
//5.import the one which lays down the infra code for websocket connection establishment and acceptance
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//6.import the one which allows us to provide the allowed connection origins and tell our websocket url
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

//class creation
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    //connect to the CollabWebSocketHandler instance created so as to use it
    @Autowired
    private CollabWebSocketHandler collabwebsockethandler;

    //override the method of implemented interface
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        registry.addHandler(collabwebsockethandler, "/ws/*").setAllowedOrigins("http://localhost:5173");
    }
}