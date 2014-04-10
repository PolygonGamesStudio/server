package GameMechanicsTests;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import gameClasses.Field;

public class GameSessionModule extends AbstractModule{

    /*
        относительно поля белые повёрнуты направо, а чёрные - налево
     */
    private final int fieldSize;
    private int lastStrokeId;
    private Field[][] currentPositions;

    public GameSessionModule(int fieldSize) {
        this.fieldSize = fieldSize;
        createEmptyField();
    }

    public void setField(int x, int y, Field.checker type, boolean isKing) {
        currentPositions[x][y].setType(type);
        if (isKing)
            currentPositions[x][y].makeKing();
    }

    public void setLastStrokeId(int id) {
        lastStrokeId = id;
    }

    @Override
    protected void configure() {
        int whiteQuantity = 0;
        int blackQuantity = 0;

        for (int i=0; i<fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (currentPositions[i][j].getType() == Field.checker.black)
                    blackQuantity++;
                else if (currentPositions[i][j].getType() == Field.checker.white)
                    whiteQuantity++;
            }
        }
        bind(Field[][].class).toInstance(currentPositions);
        bindConstant().annotatedWith(Names.named("blackQuantity")).to(blackQuantity);
        bindConstant().annotatedWith(Names.named("whiteQuantity")).to(whiteQuantity);
        bindConstant().annotatedWith(Names.named("lastStrokeId")).to(lastStrokeId);
    }

    private void createEmptyField() {
        currentPositions = new Field[fieldSize][fieldSize];
        for (int i=0; i<fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                currentPositions[i][j] = new Field(Field.checker.nothing);
            }
        }
    }
}
