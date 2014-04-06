package ChatTests;

import base.MessageSystem;
import base.WebSocket;
import chat.ChatWSImpl;
import chat.GameChatImpl;
import dbService.UserDataSet;
import frontend.UserDataImpl;
import messageSystem.MessageSystemImpl;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class ChatWSImplTest {
    private ChatWSImpl ChatWS;
    private String msg = "test msg";


    @Test
    public void testOnWebSocketTextNullConnected(){
        ChatWS = new ChatWSImpl();
        ChatWS.onWebSocketText(msg);
    }

    @Test
    public void testOnWebSocketTextFailJSON(){

        ChatWSImpl chatWSSpy = spy(new ChatWSImpl());

        when(chatWSSpy.isNotConnected()).thenReturn(false);
        chatWSSpy.onWebSocketText(msg);
    }

    @Test
    public void testOnWebSocketTextCorrectJSON(){

        ChatWSImpl chatWSSpy = spy(new ChatWSImpl());
        when(chatWSSpy.isNotConnected()).thenReturn(false);

        String serverTime = UserDataImpl.getStartServerTime();
        chatWSSpy.onWebSocketText("{\"sessionId\":\"1\", \"startServerTime\":\"" +serverTime + "\", \"text\":\"test msg\"}");
    }

    @Test
    public void testOnWebSocketTextAddChater(){

        ChatWSImpl chatWSSpy = spy(new ChatWSImpl());
        when(chatWSSpy.isNotConnected()).thenReturn(false);

        String serverTime = UserDataImpl.getStartServerTime();
        chatWSSpy.onWebSocketText("{\"sessionId\":\"1\", \"startServerTime\":\"" +serverTime + "\"}");
    }


    @Test
    public void testOnWebSocketTextUnCorrectTime(){

        ChatWSImpl chatWSSpy = spy(new ChatWSImpl());
        when(chatWSSpy.isNotConnected()).thenReturn(false);

        chatWSSpy.onWebSocketText("{\"sessionId\":\"1\", \"startServerTime\":\"123\", \"text\":\"test msg\"}");
    }
}
