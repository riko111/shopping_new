package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DB接続の共通化
public class DBconnect2 {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
	    // 接続に使用する情報
	    final String JDBC_URL = "jdbc:mysql://localhost:3306/shopping_new";
	    final String DB_USER = "root";
	    final String DB_PASS = "mysqlpa55";

		//JDBCドライバクラスをJVMに登録
	    Class.forName("com.mysql.jdbc.Driver");

		//DBに接続
		Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

		System.out.println("DB接続OK");
		return con;
	}
}