package frontend;

import base.GameMechanic;
import gameClasses.Snapshot;
import gameMechanic.GameMechanicImpl;
import gameMechanic.GameSession;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class WebSocketImplTest {
    @Test(expectedExceptions = NullPointerException.class)
    public void testOnWebSocketTextNullPointer() throws Exception {
        WebSocketImpl webSocket = spy(new WebSocketImpl(false));
        when(webSocket.isNotConnected()).thenReturn(false);
        String serverTime = UserDataImpl.getStartServerTime();
        webSocket.onWebSocketText("{\"sessionId\":\"1\", \"startServerTime\":\"" + serverTime + "\", \"from_x\" : \"1\", \"from_y\":\"1\", \"status\":\"1\", \"to_x\":\"2\", \"to_y\":\"2\"}");
    }

    @Test
    public void testNullConnection() throws Exception {
        WebSocketImpl webSocket = new WebSocketImpl(false);
        webSocket.onWebSocketText("test test");
    }

    @Test
    public void testUpdateUsersColorBlack() throws Exception {
        WebSocketImpl webSocket = new WebSocketImpl(false);
        Map<String, String> test = new HashMap();
        test.put("black", "black");
        webSocket.updateUsersColor(test);
    }

    @Test
    public void testUpdateUsersColorWhite() throws Exception {
        WebSocketImpl webSocket = new WebSocketImpl(false);
        Map<String, String> test = new HashMap();
        test.put("black", "white");
        webSocket.updateUsersColor(test);
    }

    @Test
    public void testDoneSnapshot() throws Exception {
        GameSession gameSession;
        int fieldSize = 8;
        int playerSize = 3;
        int id1 = 1;
        int id2 = 2;
        gameSession = new GameSession(id1, id2, fieldSize, playerSize);
        WebSocketImpl webSocket = new WebSocketImpl(false);
        webSocket.doneSnapshot(1, gameSession.getSnapshot(1));
    }

    @Test
    public void testAddNewWS() throws Exception {
        WebSocketImpl webSocket = new WebSocketImpl(false);
    }

    @Test
    public void testOnWebSocketTextSession() throws Exception {
        WebSocketImpl webSocket = spy(new WebSocketImpl(false));
        when(webSocket.isNotConnected()).thenReturn(false);
        String serverTime = UserDataImpl.getStartServerTime();
        webSocket.onWebSocketText("{\"sessionId\":\"1\", \"startServerTime\":\"" + serverTime + "\" }");
    }

    @Test
    public void testUpdateUsersColo() throws Exception {
        WebSocketImpl webSocket = new WebSocketImpl(false);
        Map <String, String> test = new HashMap<String, String>();
        test.put("black", "black");
        webSocket.updateUsersColor(test);

    }
}
