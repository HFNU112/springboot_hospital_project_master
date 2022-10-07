package com.lb.utils;

import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName： DrugsUtils
 * @Description：
 * @Author: 蓝莲花
 * @Date： 2020/4/5 下午2:30
 * @Version： V1.0
 **/
public class DrugsUtils {

    //获取配药信息
    public static String getDrugsInfo(Map map) {
        Iterator entries = map.entrySet().iterator();
        String ids = "";
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            String[] _key = key.split("_");
            if (_key.length > 1) {
                if (_key[1].equals("number") && !value.equals("")) {
                    ids += (_key[0] + "@" + value) + ",";
                }
            }
        }
        ids = ids.substring(0, ids.length() - 1);
        return ids;
    }
}
