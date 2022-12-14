package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {

// ■データベースと接続
	public static Connection getConnection() {
	    // 接続に使用する情報
		final String JDBC_URL = "jdbc:mysql://localhost:3306/shopping_new"; //localhost用
//		final String JDBC_URL = "jdbc:mysql://database-1.cnclrt5gavnf.ap-northeast-3.rds.amazonaws.com:3306/shopping_new?characterEncoding=UTF-8&serverTimezone=SERVER"; //RDSのエンドポイント ※EC2のDNSやIP名ではない
	    final String DB_USER = "root"; //localhost用
//	    final String DB_USER = "admin"; //RDSのマスターユーザー名
	    final String DB_PASS = "mysqlpa55";

		System.out.println("JDBC_URL：" + JDBC_URL);
		System.out.println("DB_USER：" + DB_USER);
		System.out.println("DB_PASS：" + DB_PASS);

	    try {
	    	// JDBCドライバの有効化（登録）
	    	//Class.forName("com.mysql.jdbc.Driver"); //古い書き方
	    	Class.forName("com.mysql.cj.jdbc.Driver"); //Java6以降、Class.forNameは不要だが後方互換のため残す
	    } catch (ClassNotFoundException e) {
            e.printStackTrace();
	    }

	    //データベース接続の確立
	    Connection con = null;
	    try {
			con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
//			System.out.println("DB接続OK");
	    } catch(SQLException e) {
			System.out.println("DB接続NG");
	    	e.printStackTrace();
	    }

	    return con;
	}

	// TODO:データベースを切断する処理を実装する
	// ■データベース接続の切断
//	public static void disconnect(Connection con) {
//        try{
//            con.close();
//            System.out.println("DB接続を切断した");
//        } catch (Exception ex){
//            ex.printStackTrace();
//        }
//	}


}