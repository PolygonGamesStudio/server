package frontend;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class WebSocketImplTest {
    @Test
    public void testOnWebSocketText() throws Exception {
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
