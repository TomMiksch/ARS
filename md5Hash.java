import java.security.MessageDigest;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;

public class md5Hash {

    // This class is for MD5 encryption
    // http://www.quickprogrammingtips.com/java/how-to-generate-md5-hash-in-java.html
    // MessageDigest is used. We can change to SHA if we want at anytime.
    // The input is a string; output is a hex
    //
    // In order to call the method, 
    // javac -cp pathWhereThisClassIS ***.java
    // java -cp .:pathWhereThisClassIS ***
    public static String md5(String str) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(str.getBytes("UTF-8"));
            return bytesToHex(hash);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private static String bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
}
