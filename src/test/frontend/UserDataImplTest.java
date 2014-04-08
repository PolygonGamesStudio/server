package frontend;

import base.Address;
import base.UserData;
import chat.ChatWSImpl;
import dbService.UserDataSet;
import messageSystem.MessageSystemImpl;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.*;

/**
 * Created by max on 08.04.14.
 */
public class UserDataImplTest {
    UserDataImpl userData;
    MessageSystemImpl messageSystem;
    UserDataSet userDataSet;
    @BeforeMethod
    public void setUp() throws Exception {
        messageSystem = new MessageSystemImpl();
        userData = new UserDataImpl(messageSystem);
        userDataSet = mock(UserDataSet.class);
        UserDataImpl.restartParameters();
    }

    @Test
    public void testCheckServerTime() throws Exception {

    }

    @Test
    public void testGetStartServerTime() throws Exception {

    }

    @Test
    public void testGetUserSessionBySessionId() throws Exception {

    }

    @Test
    public void testContainsSessionId() throws Exception {

    }

    @Test
    public void testCcu() throws Exception {

    }

    @Test
    public void testPutSessionIdAndUserSession() throws Exception {

    }

    @Test
    public void testGetLogInUserBySessionIdTrue() throws Exception {
        when(userDataSet.getId()).thenReturn(42);
        UserDataImpl.putLogInUser("sessionId", userDataSet);
        Assert.assertEquals(UserDataImpl.getSessionIdByUserId(42), "sessionId");
    }

    @Test
    public void testGetLogInUserBySessionIdFalse() throws Exception {
        Assert.assertNull(UserDataImpl.getSessionIdByUserId(42));
    }

    /*@Test
    public void testGetSessionIdByUserIdFalse() throws Exception {
        Assert.assertNull(UserDataImpl.getSessionIdByUserId(42));
    }*/

    @Test
    public void testGetSessionIdByUserIdTrue() throws Exception {
        when(userDataSet.getId()).thenReturn(42);
        UserDataImpl.putLogInUser("sessionId", userDataSet);
        Assert.assertTrue(UserDataImpl.getSessionIdByUserId(42).equals("sessionId"));
    }

    @Test
    public void testPutSessionIdAndChatWSTrue() throws Exception {
        UserDataImpl.putLogInUser("sessionId", userDataSet);
        UserDataImpl.putSessionIdAndChatWS("sessionId", new ChatWSImpl());
        verify(userDataSet).visit();
    }

    @Test
    public void testPutSessionIdAndChatWSFalse() throws Exception {
        UserDataImpl.putSessionIdAndChatWS("sessionId", new ChatWSImpl());
        verify(userDataSet, never()).visit();
    }

    @Test
    public void testGetWSBySessionIdTrue() throws Exception {
        RemoteEndpoint remoteEndpoint = mock(RemoteEndpoint.class);
        Session session = mock(Session.class);
        when(session.getRemote()).thenReturn(remoteEndpoint);
        WebSocketImpl ws = mock(WebSocketImpl.class);
        when(ws.getSession()).thenReturn(session);
        UserDataImpl.putSessionIdAndWS("sessionId", ws);
        Assert.assertEquals(UserDataImpl.getWSBySessionId("sessionId"), ws.getSession().getRemote());
    }

    @Test
    public void testGetWSBySessionIdNull() throws Exception {
        Assert.assertNull(UserDataImpl.getWSBySessionId("sessionId"));
    }

    @Test
    public void testGetChatWSBySessionIdTrue() throws Exception {
        RemoteEndpoint remoteEndpoint = mock(RemoteEndpoint.class);
        Session session = mock(Session.class);
        when(session.getRemote()).thenReturn(remoteEndpoint);
        ChatWSImpl ws = mock(ChatWSImpl.class);
        when(ws.getSession()).thenReturn(session);
        UserDataImpl.putSessionIdAndChatWS("sessionId", ws);
        Assert.assertEquals(UserDataImpl.getChatWSBySessionId("sessionId"), remoteEndpoint);
    }

    @Test
    public void testGetChatWSBySessionIdNull() throws Exception {
        Assert.assertNull(UserDataImpl.getChatWSBySessionId("sessionId"));
    }

    /*@Test
    public void testUpdateUserIdFalse() throws Exception {
        Assert.assertNull(UserDataImpl.getLogInUserBySessionId("sessionId"));
    }*/

    @Test
    public void testUpdateUserIdTrue() throws Exception {
        //when(userDataSet.getId()).thenReturn(777);
        UserDataImpl.putLogInUser("sessionId", userDataSet);
        Assert.assertEquals(UserDataImpl.getLogInUserBySessionId("sessionId"), userDataSet);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        userData = null;
        userDataSet = null;
        messageSystem = null;
    }
}
