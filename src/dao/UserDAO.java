package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DBconnect;
import model.UserBean;

public class UserDAO {

    public UserBean findUser(UserBean user) {
		System.out.println("....................UserDAO(findUser(user))....................");

		UserBean loginUser = null;
		// データベース接続
		Connection con = null;
		con = DBconnect.getConnection();
        try {
        	String sql = "SELECT id, userName, pass FROM user WHERE userName = ?  AND pass = ?";
            PreparedStatement pStmt = con.prepareStatement(sql);
            pStmt.setString(1, user.getUserName());
            pStmt.setString(2, user.getPass());

            System.out.println("SELECT id, userName, pass FROM user WHERE userName = \"" + user.getUserName() + "\" AND pass = \"" + user.getPass() + "\"");

    		// SQL文を実行
    		ResultSet rs = pStmt.executeQuery();

    		if (rs.next()) {
    			System.out.println("DBに一致データあり");
    			int id = rs.getInt("id");
    			String userName = rs.getString("userName");
    			String pass = rs.getString("pass");
    			loginUser = new UserBean(id, userName, pass);
    		}

        } catch (SQLException e) {
              e.printStackTrace();
//              System.out.println("DB接続しっぱい");
              return null;
        }

        System.out.println("........................................");
        return loginUser;
    }

    public boolean registerUser(UserBean user) {
		System.out.println("....................UserDAO(registerUser(user))....................");

		// データベース接続
		Connection con = null;
		con = DBconnect.getConnection();

		boolean insertResult = false;

        try {
        	System.out.println("registerUserのtry文");
        	String sql0 = "SELECT COUNT(*) FROM user WHERE userName= ? LIMIT 1;";
            PreparedStatement pstmt0 = con.prepareStatement(sql0);
            pstmt0.setString(1, user.getUserName());
    		// SQL文を実行
    		ResultSet rs = pstmt0.executeQuery();

    		int check_dup = 99; //重複チェック用（重複無しなら0）

    		// 重複が無い場合、アカウントをDBに追加
    		if(rs.next()) {
    			check_dup = rs.getInt(1);
    		}

    		if (check_dup == 0) {
	    			System.out.println("rs.getInt(1)=" + rs.getInt(1) );
	    			System.out.println("DBに重複ユーザーIDなし");

		        	String sql = "INSERT INTO user(userName, pass) VALUES(?, ?)";
		            PreparedStatement pstmt = con.prepareStatement(sql);
		            pstmt.setString(1, user.getUserName());
		            pstmt.setString(2, user.getPass());
		            System.out.println("INSERT INTO user(userName, pass) VALUES(" + user.getUserName() + ", " + user.getPass() + ")");
		            // SQL文を実行
		            int result = pstmt.executeUpdate();

		    		if (result == 1) {
		    			System.out.println("DBに1レコード追加");
		    			insertResult = true;
		    		} else {
		    			System.out.println("DBレコード追加エラー");
		    		}
	    		} else {
	    			System.out.println("DBに重複ユーザーIDあり");
	    		}

        } catch (SQLException e) {
              e.printStackTrace();
        }
        System.out.println("........................................");
        return insertResult;
    }
}
