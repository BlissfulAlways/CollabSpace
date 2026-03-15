//this file is about storing the document id and the corresponding document user connections
//its main goal is to store the mapping between the id and the users so the server knows to whom to broadcast
//the changes

//firstly write the package name for this file
package com.collabspace.CollabSpace.websocket;

//now import the component from spring framework so that we can tell the spring boot to handle the class
//beneath the @Component (spring boot automatically creates the object of this class and makes it available
//to others so we dont need to create objects)
import org.springframework.stereotype.Component;
//as we are going to store websocket sessions we need websocket session as well
import org.springframework.web.socket.WebSocketSession;

//now the mappings will be stored in map data structure
import java.util.Map;
//and the websocket sessions will be stored in set as we need something mutable and something which holds unique
//values
import java.util.Set;
//now as we are going to work with websocket, each websocket connection is handled separately by creating their
//own separate threads
//now if the two users try to get access to the same document at the same time, then inserting the users can corrupt
//the map, so we use ConcurrentHashMap to avoid conflict because of simultaneous operations, the conflict is handled
//internally by the ConcurrentHashMap
import java.util.concurrent.ConcurrentHashMap;
//now as most of the times we will be reading from the set than writing to it, we use CopyOnWriteArraySet
import java.util.concurrent.CopyOnWriteArraySet;

//as imports are done, define the class
@Component
public class RoomManager{
    //create the data storing structure
    private final Map<String, Set<WebSocketSession>> rooms = new ConcurrentHashMap<>();
    //this is private because we dont wont anyone else other from this class members access it because the methods next
    //should only use the structure
    //the final is to keep the map structure always same, the other map structure should not be created
    //content can change but not the structure
    
    //first function is to add new user websocket session means add new value inside the set to corresponding
    //document id
    public void addSession(String documentId, WebSocketSession session){
        //public because any other thing should be able to access it, call it
        //void because it just adds and returns nothing

        //the edge case is the document for which the user websocket session request for adding has came does not
        //exits
        //for this case, check if the document exists or not and if not create one and then add it
        rooms.computeIfAbsent(documentId, k -> new CopyOnWriteArraySet<>()).add(session);
        //computeIfAbsent creates new room with document id and empty set if the document is absent
    }

    //now next function is remove session
    public void removeSession(String documentId, WebSocketSession session){
        //now edge case if that the document from which the user session to remove is not present
        //first check if the room is present or not
        Set<WebSocketSession> room = rooms.get(documentId);
        if (room!=null){
            room.remove(session);
        }
    }

    //next function is get session for the server so it knows whom all to broadcast changes
    public Set<WebSocketSession> getSessions(String documentId){
        //the edge case is there is no one associated for that document id
        return rooms.getOrDefault(documentId, new CopyOnWriteArraySet<>());
    }
}