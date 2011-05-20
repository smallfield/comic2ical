package info.smallfield.comic2ical.util;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CommonUtilTest {

    @Test
    public void testGetStringFlagment() {
        String src = "これが元のコード";
        ArrayList<String> dest = new ArrayList<String>();
        dest.add("これが元のコード");
        dest.add("れが元のコード");
        dest.add("が元のコード");
        dest.add("元のコード");
        dest.add("のコード");
        dest.add("コード");
        dest.add("ード");
        dest.add("ド");
        assertEquals(dest, CommonUtil.getStringFlagment(src));
    }

}
