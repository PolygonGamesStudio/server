package MessageSystemTests;

import base.Address;
import chat.GameChatImpl;
import gameMechanic.gameCreating.MsgCreateChat;
import messageSystem.MessageSystemImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MsgToGameChatTest {
    private Address from, to;

    @BeforeMethod
    public void setUp() throws Exception {
        from = new Address();
        to = new Address();
    }

    @Test
    public void testExec() throws Exception {
        GameChatImpl gameChat = new GameChatImpl(new MessageSystemImpl());
        MsgCreateChat msgCreateChat = new MsgCreateChat(from, to, "1", "2");
        msgCreateChat.exec(gameChat);

    }
}
