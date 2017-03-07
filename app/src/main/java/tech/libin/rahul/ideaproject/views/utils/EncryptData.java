package tech.libin.rahul.ideaproject.views.utils;

import java.security.MessageDigest;

/**
 * Created by Rahul Raj on 11/2/2016.
 */

public class EncryptData {

   private static EncryptData _instance;

    public EncryptData()
    {

    }

    public static EncryptData getInstance() {
        if (_instance == null) {
            _instance = new EncryptData();
        }
        return _instance;
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String convertToSha1(String text)  {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(text.getBytes("iso-8859-1"), 0, text.length());
            byte[] sha1hash = md.digest();
            return convertToHex(sha1hash);
        } catch (Exception ex) {
            return null;
        }
    }

}
