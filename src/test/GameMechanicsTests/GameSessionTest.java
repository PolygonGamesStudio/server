package GameMechanicsTests;

import gameMechanic.GameSession;
import org.testng.annotations.Test;
import resource.GameSettings;
import resource.ResourceFactory;


public class GameSessionTest {

    @Test
    public void constructorTest() {
        int fieldSize = 100;
        int playerSize = 4;

        GameSession gameSession1 = new GameSession(1, 2, (GameSettings) ResourceFactory.instanse().getResource("settings/gameSettings.xml"));
        GameSession gameSession2 = new GameSession(1, 2, fieldSize, playerSize);
    }
}
