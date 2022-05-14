package test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
class SHA2Test2 {
	public static void main(String[] args) {
		String pass = "user01pass";
		String old = "712302345518119523175169173208162107102272116628238";
		String hash = null;

		try {
			hash = build(pass);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		System.out.println("old=" + old);
		System.out.println("new=" + hash);

		if (hash.equals(old)) {
			System.out.println("DBのパスワードと一致");
		}

	}

	public static String build (String pass) throws NoSuchAlgorithmException {
		StringBuilder buff = new StringBuilder();
		if (pass != null && !pass.isEmpty()) {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(pass.getBytes());
			byte[] digest = md.digest();

			for (byte d : digest) {
				buff.append((int)d&0xFF);
			}
		}
		return buff.toString();
		}
}