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

    @Test
    public void blackSecondTest() {
        Assert.assertTrue(gameSession.checkStroke(id1,6,5,7,4));
        Assert.assertEquals(gameSession.getNext(),'b');
    }

    @Test
    public void makeWhitePawnTheKingTest() {
        gameSession.checkStroke(id1,6,5,7,4);
        gameSession.checkStroke(id2,2,5,3,4);
        gameSession.checkStroke(id1,5,6,6,5);
        gameSession.checkStroke(id2,3,6,2,5);
        gameSession.checkStroke(id1,0,5,1,4);
        gameSession.checkStroke(id2,4,7,3,6);
        gameSession.checkStroke(id1,6,7,5,6);
        gameSession.checkStroke(id2,2,5,1,4);
        gameSession.checkStroke(id1,7,4,5,2);   // срублено две фигуры подряд
        gameSession.checkStroke(id1,5,2,3,0);   // срублено две фигуры подряд
        Assert.assertEquals(gameSession.getWhiteQuantity(), 12); // все белые фигуры целы
        Assert.assertEquals(gameSession.getBlackQuantity(), 10); // у чёрных съедено 2 фигуры
    }

    @Test
    public void makeBlackKingAndBeatTest() {
        gameSession.checkStroke(id1,4,5,5,4);
        gameSession.checkStroke(id2,4,5,5,4);
        gameSession.checkStroke(id1,5,6,4,5);
        gameSession.checkStroke(id2,2,5,1,4);
        gameSession.checkStroke(id1,6,7,5,6);
        gameSession.checkStroke(id2,1,6,2,5);
        gameSession.checkStroke(id1,4,5,3,4);
        gameSession.checkStroke(id2,5,4,3,2);
        gameSession.checkStroke(id2,3,2,1,0);
        gameSession.checkStroke(id1,4,7,5,6);
        gameSession.checkStroke(id2,1,0,5,4);

        Assert.assertEquals(gameSession.getWhiteQuantity(), 9); // все белые фигуры целы
        Assert.assertEquals(gameSession.getBlackQuantity(), 12); // у чёрных съедено 2 фигуры
    }

    @Test
    public void KingHasBeenEaten() {
        gameSession.checkStroke(id1,0,5,1,4);
        gameSession.checkStroke(id2,2,5,3,4);
        gameSession.checkStroke(id1,1,4,0,3);
        gameSession.checkStroke(id2,1,6,2,5);
        gameSession.checkStroke(id1,1,6,0,5);
        gameSession.checkStroke(id2,0,7,1,6);
        gameSession.checkStroke(id1,2,5,3,4);
        gameSession.checkStroke(id2,3,4,5,2);
        gameSession.checkStroke(id1,3,6,1,4);
        gameSession.checkStroke(id2,2,5,3,4);
        gameSession.checkStroke(id1,4,7,3,6);
        gameSession.checkStroke(id2,0,5,1,4);
        gameSession.checkStroke(id1,3,6,2,5);
        gameSession.checkStroke(id2,1,4,0,3);
        gameSession.checkStroke(id1,4,5,3,4);
        gameSession.checkStroke(id2,6,5,5,4);
        gameSession.checkStroke(id1,3,4,5,2);
        gameSession.checkStroke(id1,5,2,7,0);
        gameSession.checkStroke(id2,4,5,3,4);
        gameSession.checkStroke(id1,7,0,3,4);
        gameSession.checkStroke(id1,3,4,1,2);
        gameSession.checkStroke(id2,7,6,5,4);

        Assert.assertEquals(gameSession.getWhiteQuantity(), 10); // все белые фигуры целы
        Assert.assertEquals(gameSession.getBlackQuantity(), 7); // у чёрных съедено 2 фигуры
    }
}
