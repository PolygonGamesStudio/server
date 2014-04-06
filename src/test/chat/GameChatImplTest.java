package chat;

import base.GameChat;
import base.MessageSystem;
import frontend.WebSocketImpl;
import messageSystem.MessageSystemImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by max on 05.04.14.
 */
public class GameChatImplTest {
    @BeforeMethod
    public void setUp() throws Exception {
        MessageSystem messageSystem= new MessageSystemImpl();
        Server chatServer = new Server(8010);
        ServletContextHandler context2 = new ServletContextHandler(ServletContextHandler.SESSIONS);
        chatServer.setHandler(context2);
        context2.addServlet(new ServletHolder(new ChatWSServletImpl()),"/*");
        chatServer.start();

        WebSocketImpl webSocket = new WebSocketImpl(true);
        GameChat gameChat = new GameChatImpl(messageSystem);
        //gameChat.createChat();
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateChat() throws Exception {

    }

    @Test
    public void testSendMessage() throws Exception {

    }

    @Test
    public void testRun() throws Exception {

    }
}
