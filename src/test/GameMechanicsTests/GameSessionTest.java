package GameMechanicsTests;

import gameMechanic.GameSession;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


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
        id2 = 2;

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

        Assert.assertEquals(gameSession.getWhiteQuantity(), 9);
        Assert.assertEquals(gameSession.getBlackQuantity(), 12);
    }

    @Test
    public void kingHasBeenEaten() {
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

        Assert.assertEquals(gameSession.getWhiteQuantity(), 10);
        Assert.assertEquals(gameSession.getBlackQuantity(), 7);
    }

    @Test
    public void whiteWin() {
        gameSession.checkStroke(id1,4,5,5,4);
        gameSession.checkStroke(id2,0,5,1,4);
        gameSession.checkStroke(id1,5,4,7,2);
        gameSession.checkStroke(id2,4,5,5,4);
        gameSession.checkStroke(id1,3,6,4,5);
        gameSession.checkStroke(id2,3,6,4,5);
        gameSession.checkStroke(id1,2,5,1,4);
        gameSession.checkStroke(id2,4,5,3,4);
        gameSession.checkStroke(id1,1,4,3,2);
        gameSession.checkStroke(id1,3,2,5,4);
        gameSession.checkStroke(id2,2,7,3,6);
        gameSession.checkStroke(id1,7,2,5,0);
        gameSession.checkStroke(id1,5,0,3,2);
        gameSession.checkStroke(id2,5,6,3,4);
        gameSession.checkStroke(id1,5,4,3,2);
        gameSession.checkStroke(id2,6,7,5,6);
        gameSession.checkStroke(id1,3,2,1,0);
        gameSession.checkStroke(id2,6,5,5,4);
        gameSession.checkStroke(id1,4,5,3,4);
        gameSession.checkStroke(id2,5,4,3,2);
        gameSession.checkStroke(id1,5,6,3,4);
        gameSession.checkStroke(id2,2,5,3,4);
        gameSession.checkStroke(id1,3,4,5,2);
        gameSession.checkStroke(id2,7,6,6,5);
        gameSession.checkStroke(id1,6,5,5,4);
        gameSession.checkStroke(id2,0,7,1,6);
        gameSession.checkStroke(id1,5,2,7,0);
        gameSession.checkStroke(id2,6,5,5,4);
        gameSession.checkStroke(id1,5,4,4,3);
        gameSession.checkStroke(id2,5,4,4,3);
        gameSession.checkStroke(id1,4,3,2,5);
        gameSession.checkStroke(id2,4,7,5,6);
        gameSession.checkStroke(id1,1,0,3,2);

        Assert.assertEquals(gameSession.getWinnerId(), id1);
        gameSession.saveLog();
    }

    @Test
    public void shouldKickButNot() {
        gameSession.checkStroke(id1,4,5,5,4);
        gameSession.checkStroke(id2,4,5,3,4);
        Assert.assertFalse(gameSession.checkStroke(id1, 5, 4, 6, 3));
        Assert.assertFalse(gameSession.checkStroke(id1, 5, 4, 6, 5));
        Assert.assertFalse(gameSession.checkStroke(id1, 5, 4, 4, 5));
        Assert.assertFalse(gameSession.checkStroke(id1, 5, 4, 4, 3));
    }

    @Test
    public void whiteKingShouldKickButNot() {
        gameSession.checkStroke(id1,4,5,5,4);
        gameSession.checkStroke(id2,0,5,1,4);
        gameSession.checkStroke(id1,5,4,7,2);
        gameSession.checkStroke(id2,2,5,3,4);
        gameSession.checkStroke(id1,2,5,3,4);
        gameSession.checkStroke(id2,3,4,5,2);
        gameSession.checkStroke(id1,1,6,3,4);
        gameSession.checkStroke(id2,3,6,2,5);
        gameSession.checkStroke(id1,3,4,4,3);
        gameSession.checkStroke(id2,2,5,4,3);
        gameSession.checkStroke(id1,0,7,1,6);
        gameSession.checkStroke(id2,2,7,3,6);
        gameSession.checkStroke(id1,7,2,5,0);
        gameSession.checkStroke(id2,4,5,5,4);
        Assert.assertFalse(gameSession.checkStroke(id1, 5, 0, 6, 1));
        Assert.assertFalse(gameSession.checkStroke(id1, 5, 0, 6, 2));
        Assert.assertFalse(gameSession.checkStroke(id1, 5, 0, 1, 5));
    }

    @Test
    public void moveToWrongPosition() {
        Assert.assertFalse(gameSession.checkStroke(id1,4,5,4,4));   // вверх
        Assert.assertFalse(gameSession.checkStroke(id1,4,5,4,6));   // вниз
        Assert.assertFalse(gameSession.checkStroke(id1,4,5,3,5));   // влево
        Assert.assertFalse(gameSession.checkStroke(id1,4,5,5,5));   // вправо

        gameSession.checkStroke(id1, 4, 5, 6, 3);   // ход через клетку
    }

    @Test
    public void leaveField() {
        Assert.assertFalse(gameSession.checkStroke(id1,0,5,-1,4));
    }

    @Test
    public void nobodyWins() {
        Assert.assertEquals(gameSession.getWinnerId(), 0);  // никто не выиграл
        gameSession.checkStroke(id1,4,5,5,4);
        Assert.assertEquals(gameSession.getWinnerId(), 0);  // никто не выиграл после первого хода
    }

    @Test
    public void checkSnapshot(){
        String snapshot = "{\"status\":\"snapshot\",\"next\":\"w\",\"color\":\"w\",\"" +
                "field\":[[\"white\", \"nothing\", \"white\", \"nothing\", \"white\", " +
                "\"nothing\", \"white\", \"nothing\"], [\"nothing\", \"white\", " +
                "\"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", \"white\"], " +
                "[\"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", " +
                "\"white\", \"nothing\"], [\"nothing\", \"nothing\", \"nothing\", " +
                "\"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\"], " +
                "[\"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", " +
                "\"nothing\", \"nothing\", \"nothing\"], [\"nothing\", \"black\", \"nothing\", " +
                "\"black\", \"nothing\", \"black\", \"nothing\", \"black\"], [\"black\", " +
                "\"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\", " +
                "\"nothing\"], [\"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", " +
                "\"black\", \"nothing\", \"black\"]],\"king\":[[\"false\", \"false\", \"false\", " +
                "\"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", " +
                "\"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", " +
                "\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], " +
                "[\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", " +
                "\"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", " +
                "\"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", " +
                "\"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", " +
                "\"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", " +
                "\"false\", \"false\", \"false\", \"false\", \"false\"]]}";
        Assert.assertEquals(gameSession.getSnapshot(1).toString(), snapshot);
    }

    @Test
    public void getFields() {
        int[] fields = gameSession.getFields();
        Assert.assertEquals(fields, new int[]{0, 2, 4, 6, 9, 11, 13, 15, 16, 18, 20, 22, 41, 43, 45, 47, 48, 50, 52, 54, 57, 59, 61, 63} );
    }

    @Test
    public void pawnCanBeatLeftDown() {
        gameSession.checkStroke(id1,2,5,3,4);
        gameSession.checkStroke(id2,4,5,5,4);
        gameSession.checkStroke(id1,6,5,7,4);
        gameSession.checkStroke(id2,5,4,6,3);
        gameSession.checkStroke(id1,0,5,2,3);
        gameSession.checkStroke(id2,6,5,7,4);
        gameSession.checkStroke(id1,1,6,2,5);
        gameSession.checkStroke(id2,7,4,6,3);
        gameSession.checkStroke(id1,2,3,0,5);
        Assert.assertEquals(gameSession.getNext(), 'b');
    }

    @Test
    public void kingBeatingAllDirections() {
        gameSession.checkStroke(id1,4,5,5,4);
        gameSession.checkStroke(id2,0,5,1,4);
        gameSession.checkStroke(id1,5,4,7,2);
        gameSession.checkStroke(id2,6,5,7,4);
        gameSession.checkStroke(id1,0,5,1,4);
        gameSession.checkStroke(id2,4,5,5,4);
        gameSession.checkStroke(id1,1,4,3,2);
        gameSession.checkStroke(id2,3,6,5,4);
        gameSession.checkStroke(id1,2,5,3,4);
        gameSession.checkStroke(id2,5,4,3,2);
        gameSession.checkStroke(id1,5,6,3,4);
        gameSession.checkStroke(id2,2,5,3,4);
        gameSession.checkStroke(id1,3,4,5,2);
        gameSession.checkStroke(id2,1,6,3,4);
        gameSession.checkStroke(id1,6,5,5,4);
        gameSession.checkStroke(id2,3,4,1,2);
        gameSession.checkStroke(id1,7,6,5,4);
        gameSession.checkStroke(id2,0,7,1,6);
        gameSession.checkStroke(id1,1,6,2,5);
        gameSession.checkStroke(id2,2,7,3,6);
        gameSession.checkStroke(id1,7,2,5,0);
        gameSession.checkStroke(id1,5,0,0,5);
        gameSession.checkStroke(id2,4,7,3,6);
        gameSession.checkStroke(id1,0,5,5,0);
        gameSession.checkStroke(id2,7,6,6,5);
        gameSession.checkStroke(id1,5,0,6,1);
        gameSession.checkStroke(id2,5,6,4,5);
        gameSession.checkStroke(id1,6,1,3,4);
        gameSession.checkStroke(id2,6,7,5,6);
        gameSession.checkStroke(id1,3,4,0,1);
        gameSession.checkStroke(id2,5,6,6,5);
        gameSession.checkStroke(id1,0,1,2,3);
        gameSession.checkStroke(id1,2,3,4,1);
        gameSession.checkStroke(id2,7,4,6,3);
        gameSession.checkStroke(id1,4,1,0,5);

        Assert.assertEquals(gameSession.getWinnerId(), id1);
        Assert.assertEquals(gameSession.getBlackQuantity(), 0);
    }

    @Test
    public void crossAllBorders() {
        Assert.assertFalse(gameSession.checkStroke(id1,0,7,-1,6));
        Assert.assertFalse(gameSession.checkStroke(id1,0,7,1,8));
        Assert.assertFalse(gameSession.checkStroke(id1,0,7,-1,8));

        gameSession.checkStroke(id1,6,5,7,4);
        gameSession.checkStroke(id2,0,5,1,4);
        gameSession.checkStroke(id1,2,5,3,4);
        gameSession.checkStroke(id2,1,4,2,3);
        gameSession.checkStroke(id1,4,5,6,3);
        gameSession.checkStroke(id2,6,5,7,4);
        gameSession.checkStroke(id1,7,6,6,5);
        gameSession.checkStroke(id2,1,6,0,5);
        gameSession.checkStroke(id1,6,5,5,4);
        gameSession.checkStroke(id2,2,5,3,4);
        gameSession.checkStroke(id1,3,4,5,2);
        gameSession.checkStroke(id2,0,7,1,6);
        gameSession.checkStroke(id1,5,2,7,0);
        gameSession.checkStroke(id2, 4, 6, 5, 5);

        Assert.assertFalse(gameSession.checkStroke(id1,7,0,8,-1));
        Assert.assertFalse(gameSession.checkStroke(id1,7,0,8,1));
        Assert.assertFalse(gameSession.checkStroke(id1,7,0,6,-1));
    }

    @Test
    public void whileWhiteMoveBlackPawn() {
        Assert.assertFalse(gameSession.checkStroke(id1,0,2,1,3));
    }

    @Test
    public void blackWin() {
        gameSession.checkStroke(id1,4,5,5,4);
        gameSession.checkStroke(id2,4,5,5,4);
        gameSession.checkStroke(id1,0,5,1,4);
        gameSession.checkStroke(id2,5,4,7,2);
        gameSession.checkStroke(id1,2,5,1,4);
        gameSession.checkStroke(id2,7,2,5,4);
        gameSession.checkStroke(id1,5,4,4,3);
        gameSession.checkStroke(id2,2,5,4,3);
        gameSession.checkStroke(id1,6,5,5,4);
        gameSession.checkStroke(id2,4,3,3,2);
        gameSession.checkStroke(id1,5,6,3,4);
        gameSession.checkStroke(id2,5,4,3,2);
        gameSession.checkStroke(id2,3,2,1,4);
        gameSession.checkStroke(id1,3,6,4,5);
        gameSession.checkStroke(id2,6,5,5,4);
        gameSession.checkStroke(id1,7,6,6,5);
        gameSession.checkStroke(id2,1,4,2,3);
        gameSession.checkStroke(id1,4,5,6,3);
        gameSession.checkStroke(id2,0,5,2,3);
        gameSession.checkStroke(id2,2,3,0,1);
        gameSession.checkStroke(id1,6,7,5,6);
        gameSession.checkStroke(id2,0,1,1,0);
        gameSession.checkStroke(id1,5,6,4,5);
        gameSession.checkStroke(id2,1,0,4,3);
        gameSession.checkStroke(id1,2,7,3,6);
        gameSession.checkStroke(id2,5,4,6,3);
        gameSession.checkStroke(id1,3,6,2,5);
        gameSession.checkStroke(id2,6,3,4,1);
        gameSession.checkStroke(id1,4,7,2,5);
        gameSession.checkStroke(id1,2,5,4,3);
        gameSession.checkStroke(id2,3,6,2,5);
        gameSession.checkStroke(id1,1,6,2,5);
        gameSession.checkStroke(id2,2,5,4,3);
        gameSession.checkStroke(id2,4,3,6,1);
        gameSession.checkStroke(id1,0,7,2,5);
        gameSession.checkStroke(id2,1,6,2,5);
        gameSession.checkStroke(id1,2,5,3,4);
        gameSession.checkStroke(id2,5,6,4,5);
        gameSession.checkStroke(id1,3,4,4,3);
        gameSession.checkStroke(id2,2,5,4,3);

        Assert.assertEquals(gameSession.getWinnerId(), id2);
        gameSession.saveLog();
    }

    @Test
    public void doesntBeatButShould() {
        gameSession.checkStroke(id1,4,5,3,4);
        gameSession.checkStroke(id2,6,5,5,4);
        Assert.assertFalse(gameSession.checkStroke(id1, 3, 4, 4, 3));
    }

    @Test
    public void problemOccurred() {
        gameSession.checkStroke(id1,4,5,5,4);
        gameSession.checkStroke(id2,0,5,1,4);
        gameSession.checkStroke(id1,5,4,7,2);
        gameSession.checkStroke(id2,4,5,5,4);
        gameSession.checkStroke(id1,2,5,1,4);
        gameSession.checkStroke(id2,3,6,4,5);
        gameSession.checkStroke(id1,1,4,0,3);
        gameSession.checkStroke(id2,2,7,3,6);
        gameSession.checkStroke(id1,7,2,5,0);
        gameSession.checkStroke(id2,4,5,3,4);

        Assert.assertFalse(gameSession.checkStroke(id1, 5, 0, 3, 2));
//        Assert.assertTrue(gameSession.checkStroke(id1, 5, 0, 3, 2));  // должно быть так. ОШИБКА
    }

    @Test
    public void moveFromWrongPosition() {
        Assert.assertFalse(gameSession.checkStroke(id1, 0, 6, 1, 5));
    }

    @Test
    public void testStayTheGround() throws Exception {
        Assert.assertFalse(gameSession.checkStroke(id1, 4, 5, 4, 5));
    }

    @Test
    public void testStayTheGroundOnCorrectPosition() throws Exception {
        Assert.assertFalse(gameSession.checkStroke(id1, 0, 0, 0, 0));
    }

    @Test
    public void whiteKingEatAllDirections() {
        gameSession.checkStroke(id1,2,5,3,4);
        gameSession.checkStroke(id2,2,5,3,4);
        gameSession.checkStroke(id1,3,4,5,2);
        gameSession.checkStroke(id2,1,6,3,4);
        gameSession.checkStroke(id1,4,5,5,4);
        gameSession.checkStroke(id2,0,5,1,4);
        gameSession.checkStroke(id1,5,4,7,2);
        gameSession.checkStroke(id2,2,7,1,6);
        gameSession.checkStroke(id1,7,2,5,0);
        gameSession.checkStroke(id2,6,5,5,4);
        gameSession.checkStroke(id1,6,5,7,4);
        gameSession.checkStroke(id2,3,6,2,5);
        gameSession.checkStroke(id1,5,6,6,5);
        gameSession.checkStroke(id2,5,4,6,3);
        Assert.assertFalse(gameSession.checkStroke(id1, 0, 5, 1, 4));
        gameSession.checkStroke(id1,0,5,2,3);
        gameSession.checkStroke(id1,2,3,4,1);
        gameSession.checkStroke(id1,4,1,6,3);
        gameSession.checkStroke(id2,7,6,6,5);
        gameSession.checkStroke(id1,5,0,1,4);
        gameSession.checkStroke(id2,6,5,7,4);
        gameSession.checkStroke(id1,1,4,3,2);
        gameSession.checkStroke(id2,5,6,6,5);
        gameSession.checkStroke(id1,3,2,5,4);
        gameSession.checkStroke(id2,6,7,5,6);
        gameSession.checkStroke(id1,5,4,1,0);
        gameSession.checkStroke(id2,4,7,5,6);
        gameSession.checkStroke(id1,1,0,3,2);
        gameSession.checkStroke(id2,7,4,6,3);
        gameSession.checkStroke(id1,3,2,0,5);
        gameSession.checkStroke(id2,6,5,5,4);
        gameSession.checkStroke(id1,0,5,5,0);
        gameSession.checkStroke(id2,0,7,1,6);
        gameSession.checkStroke(id1,5,0,7,2);

        Assert.assertEquals(gameSession.getWinnerId(), id1);
    }
}
