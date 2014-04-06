package GameMechanicsTests;

import gameMechanic.GameSession;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resource.GameSettings;
import resource.ResourceFactory;


public class GameSessionTest {

    private GameSession gameSession;
    private int fieldSize;  // размер поля [fieldSize x fieldSize]
    private int playerSize; // сколько рядов шашек у игрока
    private int id1;    // id первого игрока
    private int id2;    // id второго игрока

    @BeforeMethod
    public void setUp() throws Exception {
        fieldSize = 8;
        playerSize = 3;
        id1 = 1;
        id2 = 3;

        gameSession = new GameSession(id1, id2, fieldSize, playerSize);
    }

    @Test
    public void whiteFirstTest() {
        Assert.assertEquals(gameSession.getNext(),'w');
    }
}
