package youngfriend.util;

import java.util.Collection;
import java.util.Map;

public class Map_Utils {
    public static Collection<String> getNames(Map<String,String> datas) {
        return datas.keySet();
    }


    public static String[] getNameArr(Map<String,String> datas) {
        String[] arr = new String[datas.size()];
        datas.keySet().toArray(arr);
        return arr;
    }

    public static Map.Entry<String, String> getEntryByName(Map<String,String> datas,String name) {
        if (!datas.containsKey(name)) {
            return null;
        } else {
            for (Map.Entry<String, String> entry : datas.entrySet()) {
                if (entry.getKey().equals(name)) {
                    return entry;
                }
            }
        }
        return null;
    }

    public static Map.Entry<String, String> getEntryByValue(Map<String,String> datas,String value) {
        if (!datas.containsValue(value)) {
            return null;
        } else {
            for (Map.Entry<String, String> entry : datas.entrySet()) {
                if (entry.getValue().equals(value)) {
                    return entry;
                }
            }
        }
        return null;
    }

}
