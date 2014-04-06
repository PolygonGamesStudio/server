package ChatTests;

import base.GameChat;
import base.MessageSystem;
import chat.GameChatImpl;
import dbService.UserDataSet;
import frontend.UserDataImpl;
import messageSystem.MessageSystemImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GameChatImplTest {
    private MessageSystem messageSystem;
    private GameChat gameChat;
    UserDataSet userDataSet;
    private String sessionIdOne, sessionIdTwo, sessionIdThree, sessionIdNull;

    @BeforeMethod
    public void setUp() {
        messageSystem = new MessageSystemImpl();
        gameChat = new GameChatImpl(messageSystem);
        sessionIdOne = "1";
        sessionIdTwo = "2";
        sessionIdThree = "3";
        sessionIdNull = null;
    }


    @Test
    public void testSendMessageCorrect(){
        gameChat.createChat(sessionIdOne, sessionIdTwo);
        UserDataImpl.putLogInUser(sessionIdOne, new UserDataSet());

        GameChatImpl.sendMessage(sessionIdOne, "test message");
    }

    @Test
    public void testSendMessageNull(){
        gameChat.createChat(sessionIdOne, sessionIdTwo);
        UserDataImpl.putLogInUser(sessionIdThree, new UserDataSet());

        GameChatImpl.sendMessage(sessionIdThree, "test message");
    }

}
