package UtilsTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.VFS;

public class VFSTests {
    String relativePath =  "statistic/time";

    @Test
    public void testWriteToEndOfFile() throws Exception {
        VFS.writeToEndOfFile("/statistic/empty", "end of file");
        Assert.assertTrue(VFS.readFile("/statistic/empty").endsWith("end of file"));
    }

    @Test
    public void testReadAndWrite() throws Exception {
        VFS.writeToFile("/statistic/empty", "test");
        Assert.assertEquals(VFS.readFile("/statistic/empty"), "test");
    }

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
