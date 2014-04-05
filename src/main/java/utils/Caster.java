package utils;

import java.util.Map;

public class Caster{
	public static String[] castKeysToStrings(Map<String, ?> map){
        // TODO: check map for null pointer exception
		String[] mas = new String[map.size()];
		int count=0;
		for(String element : map.keySet()){
			mas[count] = element;
			count+=1;
		}
		return mas;
	}
}
