package MessageSystemTests;

import base.Address;
import frontend.WebSocketImpl;
import gameClasses.Field;
import gameClasses.Snapshot;
import gameMechanic.Stroke.MsgDoneSnapshot;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class MsgDoneSnapshotTest {
    private Address from, to;
    private Field[][] field;
    private char color;
    private char next;
    private int fieldSize;

    @BeforeMethod
    public void setUp() throws Exception {
        this.color = 'b';
        this.fieldSize = 4;
        this.next = 'w';
        this.field = new Field[this.fieldSize][this.fieldSize];
        for(int i = 0; i < this.fieldSize; i++){
            for (int j = 0; j < this.fieldSize; j++){
                field[i][j] = new Field();
            }
        }

        from = new Address();
        to = new Address();

    }

    @Test
    public void testExec() throws Exception {
        Snapshot snapshot = new Snapshot(this.field, this.color, this.fieldSize, this.next);
        MsgDoneSnapshot msgDoneSnapshot = new MsgDoneSnapshot(from, to, 1, snapshot);
        WebSocketImpl webSocket = spy(new WebSocketImpl(false));
        when(webSocket.isNotConnected()).thenReturn(false);
        msgDoneSnapshot.exec(webSocket);
    }
}
