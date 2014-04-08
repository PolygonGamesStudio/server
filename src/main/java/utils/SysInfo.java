package utils;

import java.util.HashMap;
import java.util.Map;

import frontend.UserDataImpl;

public class SysInfo implements Runnable{
	private Runtime runtime = Runtime.getRuntime();
	private String lastDate;
	private static Map<String, String> data =
			new HashMap<String, String>();

	public SysInfo(){
		data.put("MemoryUsage", "/statistic/memoryUsage");
		data.put("TotalMemory", "/statistic/totalMemory");
		data.put("Time", "/statistic/time");
		data.put("CCU", "/statistic/ccu");
	}

	public static String getStat(String service){
		return ("["+VFS.readFile(data.get(service))+"]");
	}

	public void run(){
        lastDate=TimeHelper.getTime();
        VFS.writeToFile(data.get("MemoryUsage"), String.valueOf((int) (runtime.totalMemory()-runtime.freeMemory())));
        VFS.writeToFile(data.get("TotalMemory"), String.valueOf((int) (runtime.totalMemory())));
        VFS.writeToFile(data.get("Time"), lastDate);
				VFS.writeToFile(data.get("CCU"), String.valueOf(UserDataImpl.ccu()));
        while (true){
            TimeHelper.sleep(10000);
            lastDate=TimeHelper.getTime();
            VFS.writeToEndOfFile(data.get("MemoryUsage"), ","+String.valueOf((int) (runtime.totalMemory()-runtime.freeMemory())));
            VFS.writeToEndOfFile(data.get("TotalMemory"), ","+String.valueOf((int) (runtime.totalMemory())));
            VFS.writeToEndOfFile(data.get("Time"), ","+lastDate);
            VFS.writeToEndOfFile(data.get("CCU"), ","+String.valueOf(UserDataImpl.ccu()));
		}
	}
}