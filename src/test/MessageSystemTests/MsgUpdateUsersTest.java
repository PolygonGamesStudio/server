package MessageSystemTests;

import base.Address;
import dbService.MsgUpdateUsers;
import dbService.UserDataSet;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MsgUpdateUsersTest {
    MsgUpdateUsers msgUpdateUsers;
    private Address from, to;
    private List<UserDataSet> users;

    @BeforeMethod
    public void setUp() throws Exception {
        from = new Address();
        to = new Address();
        users = new ArrayList<UserDataSet>();
    }

    @Test
    public void testExec() throws Exception {
        users.add(1, new UserDataSet(1, "Test", 500, 5, 5));
        msgUpdateUsers = new MsgUpdateUsers(from, to, users);

    }
}
