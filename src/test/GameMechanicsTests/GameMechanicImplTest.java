package GameMechanicsTests;

import base.GameMechanic;
import base.MessageSystem;
import dbService.UserDataSet;
import gameMechanic.GameMechanicImpl;
import messageSystem.MessageSystemImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameMechanicImplTest {

    GameMechanic gameMechanic;
    private Map<String, UserDataSet> users;
    Map<String,String> sessionIdToColor;

    private int id1;
    private int id2;

    private String firstUserNick;
    private String secondUserNick;

    @BeforeMethod
    public void setUp() throws Exception {
        MessageSystem messageSystem = new MessageSystemImpl();
        gameMechanic = new GameMechanicImpl(messageSystem);
        users = new HashMap<String, UserDataSet>();

        id1 = 1;
        id2 = 2;

        firstUserNick = "firstPlayer";
        secondUserNick = "secondPlayer";

        users.put(firstUserNick, new UserDataSet(id1, firstUserNick, 500, 5, 5));
        users.put(secondUserNick, new UserDataSet(id2, secondUserNick, 300, 10, 10));
    }

    @Test
    public void testCreateGamesWithoutUsers() throws Exception {
        Map<String, UserDataSet> emptyUsers = new ConcurrentHashMap<String, UserDataSet>();

        Assert.assertEquals(gameMechanic.createGames(emptyUsers, false).size(), 0);
    }

    @Test
    public void testOnePlayerOnly() throws Exception {
        users.remove(secondUserNick);
        sessionIdToColor = gameMechanic.createGames(users, false);
        Assert.assertEquals(sessionIdToColor.size(), 0);    // нет сессии для одного игрока
    }

    @Test
    public void testTwoPlayerGame() throws Exception {
        sessionIdToColor = gameMechanic.createGames(users, false);
        Assert.assertEquals(sessionIdToColor.size(), 2);
        String colors = "";
        for (String key: sessionIdToColor.keySet()) {
            Assert.assertNotNull(sessionIdToColor.get(key));
            colors += sessionIdToColor.get(key);
        }
        Assert.assertTrue(colors.contains("white"));
        Assert.assertTrue(colors.contains("black"));
    }

    @Test
    public void testCheckStroke() throws Exception {

    }

    @Test
    public void testGetSnapshot() throws Exception {

    }

    @Test
    public void testRemoveUser() throws Exception {

    }
}
