package ChatTests;

import chat.ChatWSImpl;
import frontend.UserDataImpl;
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
    public void testOnWebSocketTextNullJSON(){

        ChatWSImpl chatWSSpy = spy(new ChatWSImpl());
        when(chatWSSpy.isNotConnected()).thenReturn(false);

        String serverTime = UserDataImpl.getStartServerTime();
        chatWSSpy.onWebSocketText("{\"sessionId\":\"null\", \"startServerTime\":\"null\", \"text\":\"\"}");
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

        chatWSSpy.onWebSocketText("{\"sessionId\":\"1\", \"startServerTime\":\"123\", \"text\":\"null\"}");
    }

    @Test
    public void testOnWebSocketTextJsonNull(){

        ChatWSImpl chatWSSpy = spy(new ChatWSImpl());
        when(chatWSSpy.isNotConnected()).thenReturn(false);

        chatWSSpy.onWebSocketText("{\"sessionId\":\"null\", \"startServerTime\":\"null\", \"text\":\"null\"}");
    }
}
