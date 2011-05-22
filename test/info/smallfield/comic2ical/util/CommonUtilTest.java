package info.smallfield.comic2ical.util;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.DecoderException;
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

    @Test
    public void testEnclipt() throws InvalidKeyException,
            IllegalBlockSizeException, NoSuchAlgorithmException,
            UnsupportedEncodingException, BadPaddingException,
            NoSuchPaddingException {
        String src = "238";
        try {
            assertEquals(src, CommonUtil.decrypt(CommonUtil.encrypt(src)));
        } catch (DecoderException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

        System.out.println(CommonUtil.generateShortenString("すごい長い文章か何か"));
    }

}
