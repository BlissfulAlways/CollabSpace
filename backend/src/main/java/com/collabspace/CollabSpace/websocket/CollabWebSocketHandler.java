//write where the file lives
package com.collabspace.CollabSpace.websocket;

//import things
//1.import the thing which tells spring boot to handle the class
import org.springframework.stereotype.Component;
//2.import the one which allows using binary web socket
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
//3.import the one which will allow connecting to the instance of RoomManger
import org.springframework.beans.factory.annotation.Autowired;
//4. we get session, import the one which gives websocket session
import org.springframework.web.socket.WebSocketSession;
//5.import the one which allows wrapping the binary message into object to broadcast
import org.springframework.web.socket.BinaryMessage;
//6. we get session closed, import the one which allows knowing when and why the session closed
import org.springframework.web.socket.CloseStatus;

//7.import the input output exception handler
import java.io.IOException;
//8.import the set as we will be working with websocket sessions set from room manager
import java.util.Set;

//create class
@Component
public class CollabWebSocketHandler extends BinaryWebSocketHandler{
    //connect to the room manager instance
    @Autowired
    private RoomManager roommanager;

    //the file does these things - gets document id from uri, adds the session to the set, broadcasts data to others
    //and removes session when connection closes by calling methods from room manager based on conditions

    //1. get document id
    //url looks like "http://localhost:5173/ws/123"
    //all the numbers after the last / form the document id
    private String getDocumentId(WebSocketSession session){
        String path = session.getUri().getPath(); //gives /ws/123
        return path.substring(path.lastIndexOf('/')+1); //gives 123
    }

    //now as we have got id, lest add the session to corresponding doc id
    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        //take the document id
        String documentId = getDocumentId(session);
        roommanager.addSession(documentId, session);
    }

    //now the session has been added
    //now handle data broadcasting when binary message arrives
    //get binary data, wrap in binary object, get the sessions set for the document id, broadcast the data to
    //everyone expect the sender
    @Override
    protected void handleBinaryMessage(WebSocketSession sender, BinaryMessage message){
        //get document id
        String documentId = getDocumentId(sender);
        //get all the active sessions list for the document id
        Set<WebSocketSession> sessions = roommanager.getSessions(documentId);
        //now loop through every session and send the binary message expect to the sender
        for (WebSocketSession session : sessions){
            //check if the connection is still alive as it can happen the connection has been just closed but not 
            //yet removed from the list
            //and check if the session string is not same as the sender's session string
            if (session.isOpen() && !session.getId().equals(sender.getId())){
                try{
                    session.sendMessage(message);
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        } 
    }

    //now remove the session code
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        //get document id so as to remove from its set the session
        String documentId = getDocumentId(session);
        //remove the session
        roommanager.removeSession(documentId, session);
    }
}