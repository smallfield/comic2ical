package info.smallfield.comic2ical.util;

import java.util.ArrayList;
import java.util.List;

public class CommonUtil {
    public static List<String> getStringFlagment(String src) {
        List<String> res = new ArrayList<String>();
        int length = src.length();
        for (int i = 0; i < length; i++) {
            res.add(src.substring(i).toLowerCase());
        }
        return res;
    }
}
