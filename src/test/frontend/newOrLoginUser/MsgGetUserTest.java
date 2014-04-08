package frontend.newOrLoginUser;

import base.Abonent;
import base.Address;
import base.DataAccessObject;
import base.MessageSystem;
import messageSystem.MessageSystemImpl;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

/**
 * Created by max on 07.04.14.
 */
public class MsgGetUserTest {
    MsgGetUser msgGetUser;
    DataAccessObject dbService;
    MessageSystem messageSystem;
    Address from, to;
    Abonent mockedAbonent1, mockedAbonent2;
    @BeforeMethod
    public void setUp() throws Exception {
        from = mock(Address.class);
        to = mock(Address.class);
        msgGetUser = new MsgGetUser(from, to, "sessionId", "Nagibator9000", "qwerty123");
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

    @Test
    public void testExecFalse() throws Exception {
        when(dbService.addUDS("Nagibator9000", "qwerty123")).thenReturn(false);
        msgGetUser.exec(dbService);
        Assert.assertTrue(messageSystem.getMessages().containsKey(from));
        verify(dbService).getUDS("Nagibator9000", "qwerty123");
    }

    @Test
    public void testExecTrue() throws Exception {
        when(dbService.addUDS("Nagibator9000", "qwerty123")).thenReturn(true);
        msgGetUser.exec(dbService);
        Assert.assertTrue(messageSystem.getMessages().containsKey(from));
        verify(dbService).getUDS("Nagibator9000", "qwerty123");
    }
}
