package UtilsTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.CookieDescriptor;

import javax.servlet.http.Cookie;

public class CookieDescriptorTest {

    private Cookie[] mCookies;
    private CookieDescriptor mCookieDescriptor;

    private static String cookieName1 = "name";
    private static String cookieValue1 = "flexo";

    private static String cookieName2 = "id";
    private static String cookieValue2 = "1";

    @BeforeMethod
    public void setUp() throws Exception {
        mCookies = new Cookie[] {new Cookie(cookieName1, cookieValue1), new Cookie(cookieName2, cookieValue2)};
        mCookieDescriptor = new CookieDescriptor(mCookies);
    }

    @Test
    public void getExistingCookie() {
        Assert.assertEquals(mCookieDescriptor.getCookieByName(cookieName1), cookieValue1);
        Assert.assertEquals(mCookieDescriptor.getCookieByName(cookieName2), cookieValue2);
    }

    @Test
    public void getAbsentCookie() {
        Assert.assertNull(mCookieDescriptor.getCookieByName("some random name"));
    }
}
