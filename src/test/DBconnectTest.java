package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DB接続の共通化
public class DBconnectTest {
	public static void main(String[] args) {
		System.out.println("....................DBconnectTest....................");

		dbCheck();
	}
	public static void dbCheck() {
	    // 接続に使用する情報
//		final String JDBC_URL = "jdbc:mysql://localhost:3306/shopping_new"; //localhost用
//		final String JDBC_URL = "jdbc:mysql://database-1.cnclrt5gavnf.ap-northeast-3.rds.amazonaws.com:3306/shopping_new?serverTimezone=JST"; //RDSのエンドポイント
//		final String JDBC_URL = "jdbc:mysql://database-1.cnclrt5gavnf.ap-northeast-3.rds.amazonaws.com:3306/shopping_new?characterEncoding=UTF-8&serverTimezone=JST"; //RDSのエンドポイント
//		//最近はJST使えないみたい。SERVERを指定すると、サーバータイムゾーンとなる
		final String JDBC_URL = "jdbc:mysql://database-1.cnclrt5gavnf.ap-northeast-3.rds.amazonaws.com:3306/shopping_new?characterEncoding=UTF-8&serverTimezone=SERVER"; //RDSのエンドポイント
//	    final String DB_USER = "root";
	    final String DB_USER = "admin";
	    final String DB_PASS = "mysqlpa55";

	    try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	    	//■userテーブル
    		String sql = "SELECT * FROM user";
    		PreparedStatement pstmt = con.prepareStatement(sql);
    		ResultSet rs = pstmt.executeQuery();

    		while (rs.next()) {
    			int id = rs.getInt("id");
    			String userName = rs.getString("userName");
    			String pass = rs.getString("pass");

    			System.out.println(id + ", " + userName + ", " + pass);
    		}

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}