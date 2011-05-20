package info.smallfield.comic2ical.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class AmazonUtilTest {

    @Test
    public void testFindItem() {
        AmazonUtil au;
        try {
            au = new AmazonUtil();
            System.out.println(au.findItem("STEEL BALL RUN スティール・ボール・ラン(24)"));
        } catch (InvalidKeyException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

    }

}
