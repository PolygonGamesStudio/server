package UtilsTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.VFS;

public class VFSTests {
    @Test
    public void testgetAbsolutePathDir() throws Exception {
        String dir=System.getProperty("user.dir")+ "/";
        String path = VFS.getAbsolutePath(dir + "test");
        Assert.assertNotEquals("", path);
    }

    @Test
    public void testgetAbsolutePathFile() throws Exception {
        String path = VFS.getAbsolutePath("/test.txt");
        Assert.assertNotEquals("", path);
    }
    @Test
    public void testgetRelativePathDir() throws Exception {
        String dir=System.getProperty("user.dir")+ "/";
        String path = VFS.getRelativePath(dir + "test");
        Assert.assertNotEquals("", path);
    }

    @Test
    public void testgetRelativePathFile() throws Exception {
        String path = VFS.getRelativePath("/test.txt");
        Assert.assertNotEquals("", path);
    }
}
