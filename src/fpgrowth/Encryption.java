package fpgrowth;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Encryption {
	
	public static String encryptWithMD5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(str.getBytes());
			BigInteger num = new BigInteger(1, messageDigest);
			String hashText = num.toString(16);
			while (hashText.length() < 32) {
				hashText = "0" + hashText;
			}
			return hashText;
		} catch (Exception e) {
			
		}
		return "";
	}

}
