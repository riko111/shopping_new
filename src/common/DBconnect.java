package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DB接続の共通化
public class DBconnect {
	public static Connection getConnection() {
	    // 接続に使用する情報
	    final String JDBC_URL = "jdbc:mysql://localhost:3306/shopping_new";
	    final String DB_USER = "root";
	    final String DB_PASS = "mysqlpa55";


	    try {
	    	// JDBCドライバの有効化
	    	Class.forName("com.mysql.jdbc.Driver");
	    } catch(ClassNotFoundException e) {
            e.printStackTrace();
	    }

	    Connection con = null;
	    //データベース接続の確立
	    try {
			con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			System.out.println("DB接続OK");
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    }
//	    } finally {
//	    	// データベース接続の切断 //これでいいか要確認
//	    	if(con != null) {
//	    		try {
//	    			con.close();
//		    	} catch(SQLException e) {
//		    		e.printStackTrace();
//		    	}
//	    	}
//	    }
	    return con;
	}
}