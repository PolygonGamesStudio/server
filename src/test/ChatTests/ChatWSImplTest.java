package ChatTests;

import base.MessageSystem;
import chat.ChatWSImpl;
import chat.GameChatImpl;
import dbService.UserDataSet;
import frontend.UserDataImpl;
import messageSystem.MessageSystemImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class ChatWSImplTest {
    private ChatWSImpl ChatWS;
    private String sessionIdOne = "1";
    private String msg = "test msg";

    @BeforeMethod
    public void setUp() {
        ChatWS = new ChatWSImpl();
    }


    @Test
    public void testOnWebSocketTextNullConnected(){
        ChatWS.onWebSocketText(msg);
    }

    @Test
    public void testOnWebSocketTextCorrect(){
        System.out.println("Замочить websocket");
    }

    @Test
    public void testSendMsgNull() throws Exception {
        ChatWSImpl.sendMessage(sessionIdOne, msg);

    }
}
