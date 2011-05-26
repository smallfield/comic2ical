package info.smallfield.comic2ical.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
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
    private static char[] chars = {
        'a',
        'b',
        'c',
        'd',
        'e',
        'f',
        'g',
        'h',
        'i',
        'j',
        'k',
        'l',
        'm',
        'n',
        'o',
        'p',
        'q',
        'r',
        's',
        't',
        'u',
        'v',
        'w',
        'x',
        'y',
        'z',
        'A',
        'B',
        'C',
        'D',
        'E',
        'F',
        'G',
        'H',
        'I',
        'J',
        'K',
        'L',
        'M',
        'N',
        'O',
        'P',
        'Q',
        'R',
        'S',
        'T',
        'U',
        'V',
        'W',
        'X',
        'Y',
        'Z',
        '0',
        '1',
        '2',
        '3',
        '4',
        '5',
        '6',
        '7',
        '8',
        '9' };

    public static String key = ConfigureUtil.getInstance().get(
        ConfigureUtil.CRYPT_KEY);

    public static List<String> getStringFlagment(String src) {
        List<String> res = new ArrayList<String>();
        int length = src.length();
        for (int i = 0; i < length; i++) {
            res.add(src.substring(i).toLowerCase());
        }
        return res;
    }

    public static String encrypt(String text) throws IllegalBlockSizeException,
            InvalidKeyException, NoSuchAlgorithmException,
            UnsupportedEncodingException, BadPaddingException,
            NoSuchPaddingException {
        SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, sksSpec);
        byte[] encrypted = cipher.doFinal(text.getBytes());
        return URLEncoder.encode(
            new String(Base64.encodeBase64(encrypted)),
            "UTF-8");
    }

    public static String decrypt(String encrypted)
            throws IllegalBlockSizeException, InvalidKeyException,
            NoSuchAlgorithmException, UnsupportedEncodingException,
            BadPaddingException, NoSuchPaddingException, DecoderException {
        encrypted = URLDecoder.decode(encrypted, "UTF-8");
        SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, sksSpec);
        byte[] decrypted =
            cipher.doFinal(Base64.decodeBase64(encrypted.getBytes()));
        return new String(decrypted);
    }

    public static String generateShortenString(String src) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(src.getBytes());
            byte[] hash = md.digest();
            return base36Encode(hash).substring(0, 7);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String base36Encode(byte[] l) {
        StringBuffer ret = new StringBuffer();
        int i;
        for (byte b : l) {
            i = Math.abs((int) b);
            ret.append(chars[i % chars.length]);
        }
        return ret.toString();
    }

}
