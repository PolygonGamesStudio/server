package frontend;

import base.Address;
import base.UserData;
import chat.ChatWSImpl;
import dbService.UserDataSet;
import messageSystem.MessageSystemImpl;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.powermock.api.mockito.PowerMockito;
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
        messageSystem = mock(MessageSystemImpl.class);
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

    @Test
    public void testGetSessionIdByUserIdFalse() throws Exception {
        Assert.assertNull(UserDataImpl.getSessionIdByUserId(42));
    }

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

    @Test
    public void testUpdateUserIdFalse() throws Exception {
        UserDataImpl.putSessionIdAndUserSession("sessionId", userDataSet);
        when(userDataSet.getId()).thenReturn(777);
        UserDataSet userDataSetNew = mock(UserDataSet.class);
        userData.updateUserId("sessionId", userDataSetNew);

    }

    @Test
    public void testUpdateUserIdNull() throws Exception {
        UserDataImpl.putSessionIdAndUserSession("sessionId", userDataSet);
        userData.updateUserId("sessionId", null);
    }

    @Test
    public void testUpdateUserIdTrue() throws Exception {
        Address gm = new Address();
        when(messageSystem.getAddressByName("GameMechanic")).thenReturn(gm);
        when(userDataSet.getId()).thenReturn(777);
        UserDataImpl.putSessionIdAndUserSession("sessionId", userDataSet);
        UserDataImpl.putLogInUser("sessionId", userDataSet);

        UserDataSet userDataSetNew = mock(UserDataSet.class);
        when(userDataSetNew.getId()).thenReturn(777);
        UserDataImpl.putSessionIdAndUserSession("sessionIdNew", userDataSetNew);
        userData.updateUserId("sessionIdNew", userDataSetNew);
    }

    @Test
    public void testPartyEndWithNotEqRating() throws Exception {
        when(userDataSet.getId()).thenReturn(777);
        UserDataImpl.putSessionIdAndUserSession("sessionId", userDataSet);
        UserDataImpl.putLogInUser("sessionId", userDataSet);
        when(userDataSet.getRating()).thenReturn(666);

        UserDataSet userDataSetNew = mock(UserDataSet.class);
        when(userDataSetNew.getId()).thenReturn(888);
        UserDataImpl.putSessionIdAndUserSession("sessionIdNew", userDataSetNew);
        UserDataImpl.putLogInUser("sessionIdNew", userDataSetNew);
        when(userDataSetNew.getRating()).thenReturn(228);
        userData.partyEnd(888, 777);
    }

    @Test
    public void testPartyEndWithEqRating() throws Exception {
        when(userDataSet.getId()).thenReturn(777);
        UserDataImpl.putSessionIdAndUserSession("sessionId", userDataSet);
        UserDataImpl.putLogInUser("sessionId", userDataSet);

        UserDataSet userDataSetNew = mock(UserDataSet.class);
        when(userDataSetNew.getId()).thenReturn(888);
        UserDataImpl.putSessionIdAndUserSession("sessionIdNew", userDataSetNew);
        UserDataImpl.putLogInUser("sessionIdNew", userDataSetNew);
        userData.partyEnd(888, 777);
    }

    @Test
    public void testPartyEndWithNullSessions() throws Exception {
        when(userDataSet.getId()).thenReturn(777);
        UserDataImpl.putLogInUser("sessionId", userDataSet);

        UserDataSet userDataSetNew = mock(UserDataSet.class);
        when(userDataSetNew.getId()).thenReturn(888);
        UserDataImpl.putLogInUser("sessionIdNew", userDataSetNew);
        userData.partyEnd(888, 777);
    }





    @AfterMethod
    public void tearDown() throws Exception {
        userData = null;
        userDataSet = null;
        messageSystem = null;
    }
}
