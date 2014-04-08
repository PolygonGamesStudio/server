package UtilsTest;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.SysInfo;
import utils.TimeHelper;
import utils.VFS;

import java.io.File;

public class SysInfoTest {
    SysInfo sysInfo;

    @Test
    public void testSysInfo() throws Exception {
        sysInfo = new SysInfo();
        File file_name = new File(VFS.getAbsolutePath("/statistic/ccu"));
        file_name.delete();
        Assert.assertTrue(!file_name.exists());
        (new Thread(sysInfo)).start();
        TimeHelper.sleep(2666);
        String testFileContent = VFS.readFile(file_name.getPath());

        Assert.assertTrue(file_name.exists());
        TimeHelper.sleep(5333);

        Assert.assertFalse(testFileContent.equals(VFS.readFile(file_name.getPath())));

    }

    @Test
    public void testGetStat() throws Exception {
        String s = SysInfo.getStat("MemoryUsage");
        Assert.assertNotNull(s);
        Assert.assertTrue(s.startsWith("["));
        Assert.assertTrue(s.endsWith("]"));
    }
}
