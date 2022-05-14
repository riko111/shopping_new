package test;

import java.security.MessageDigest;
class SHA2Test {
	public static void main(String[] args) {

		String hashed_text = "ecb666d778725ec97307044d642bf4d160aabb76f56c0069c71ea25b1e926825";
		System.out.println("hashed_text=" + hashed_text);

        String text = "hoge";

        byte[] cipher_byte;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());
            cipher_byte = md.digest();
            StringBuilder sb = new StringBuilder(2 * cipher_byte.length);
            for(byte b: cipher_byte) {
                    sb.append(String.format("%02x", b&0xff) );
            }
            System.out.println("sb=" + sb);

            if (hashed_text.equals(new StringBuilder(sb).toString())) {
            	System.out.println("ハッシュ値が一致！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

	}
}