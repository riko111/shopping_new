package test;

import java.security.MessageDigest;

public class PassHashTest {
	public static void main(String[] args) {

		String str = "あいうえお";
		System.out.println("str=" + str);

		// DBに保存されたハッシュ化文字
		String db_str = "[B@2ff5659e";
		byte[] hashed_str = null;


		try { MessageDigest md = MessageDigest.getInstance("SHA-256");
			// ■ハッシュ化
	    	hashed_str = md.digest(str.getBytes());
	    	System.out.println("hashed_str=" + hashed_str);

	    	if(hashed_str.equals(db_str)) {
	    		System.out.println("一致");
	    	}

	    	// ■
//	    	md.update(hashed_str);



	    } catch (Exception e) {
	    	e.printStackTrace();
	    }



	}
}
