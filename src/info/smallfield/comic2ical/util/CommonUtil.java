package info.smallfield.comic2ical.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;

public class CommonUtil {
    public static List<String> getStringFlagment(String src) {
        List<String> res = new ArrayList<String>();
        int length = src.length();
        for (int i = 0; i < length; i++) {
            res.add(src.substring(i).toLowerCase());
        }
        return res;
    }

    public static String encrypt(String key, String text)
            throws IllegalBlockSizeException, InvalidKeyException,
            NoSuchAlgorithmException, UnsupportedEncodingException,
            BadPaddingException, NoSuchPaddingException {
        SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, sksSpec);
        byte[] encrypted = cipher.doFinal(text.getBytes());
        return new String(Base64.encodeBase64(encrypted));
    }

    public static String decrypt(String key, String encrypted)
            throws IllegalBlockSizeException, InvalidKeyException,
            NoSuchAlgorithmException, UnsupportedEncodingException,
            BadPaddingException, NoSuchPaddingException, DecoderException {
         SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, sksSpec);
        byte[] decrypted = cipher.doFinal(Base64.decodeBase64(encrypted.getBytes()));
        return new String(decrypted);
    }

}
