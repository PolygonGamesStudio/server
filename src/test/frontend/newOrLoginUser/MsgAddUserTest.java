package frontend.newOrLoginUser;

import base.*;
import messageSystem.MessageSystemImpl;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by max on 07.04.14.
 */
public class MsgAddUserTest {
    MsgAddUser msgAddUser;
    DataAccessObject dbService;
    MessageSystem messageSystem;
    Address from, to;
    MsgUpdateUser msgUpdateUser;
    Abonent mockedAbonent1, mockedAbonent2;
    @BeforeMethod
    public void setUp() throws Exception {
        from = mock(Address.class);
        to = mock(Address.class);
        msgUpdateUser = mock(MsgUpdateUser.class);
        msgAddUser = new MsgAddUser(new Address(), to, "sessionId", "Nagibator9000", "qwerty123");
        dbService = mock(DataAccessObject.class);




        messageSystem = new MessageSystemImpl();
        when(dbService.getMessageSystem()).thenReturn(messageSystem);
        mockedAbonent1 = mock(Abonent.class);
        mockedAbonent2 = mock(Abonent.class);
        when(mockedAbonent1.getAddress()).thenReturn(from);
        when(mockedAbonent2.getAddress()).thenReturn(to);

        Address from = new Address();
        Address to = new Address();

        messageSystem.addService(mockedAbonent1, "mockedAbonent1");

        messageSystem.addService(mockedAbonent2, "mockedAbonent2");

    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testExecTrue() throws Exception {
        when(dbService.addUDS("Nagibator9000", "qwerty123")).thenReturn(true);
        msgAddUser.exec(dbService);

    }

    @Test
    public void testExecFalse() throws Exception {
        when(dbService.addUDS("Nagibator9000", "qwerty123")).thenReturn(false);
        msgAddUser.exec(dbService);

    }
}
