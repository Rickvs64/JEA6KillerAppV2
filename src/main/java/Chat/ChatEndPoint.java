package Chat;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ApplicationScoped
@ServerEndpoint(value="/chat/{username}")
public class ChatEndPoint {

    private static Set<Session> users = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(
            Session session,
            @PathParam("username") String username) throws IOException, EncodeException {

        users.add(session);

        for (Session user: users) {
            user.getBasicRemote().sendText(username + " joined the room.");
        }
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException, EncodeException {
        for (Session user: users) {
            user.getBasicRemote().sendText(message);
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {

        users.remove(session);
        // Currently missing data for notifying other users that someone left the chat
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
}