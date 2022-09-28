package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.DBconnect;
import model.HistoryBean;

public class HistoryDAO {

    public List<HistoryBean> getHistory(int user_id) {
		System.out.println("....................HistoryDAO(getHistory(loginUser))....................");

		List<HistoryBean> historyList = new ArrayList<>();

		// データベース接続
		Connection con = null;
        con = DBconnect.getConnection();

        try {
    		String sql = "SELECT history.id, item_id, item_price, order_num, order_date, name AS item_name, item_price * order_num AS 'sum_price' "
    				+ "FROM history LEFT JOIN item " //外部結合のLEFT JOIN（左側に指定された表のすべての行を表示）
    				+ "ON history.item_id = item.id "
    				+ "WHERE user_id=? ORDER BY id DESC";

    		System.out.println("SELECT history.id, item_id, item_price, order_num, order_date, name AS item_name, item_price * order_num AS 'sum_price' "
    				+ "FROM history LEFT JOIN item "
    				+ "ON history.item_id = item.id "
    				+ "WHERE user_id=" + user_id + " ORDER BY id DESC");

            PreparedStatement pStmt = con.prepareStatement(sql);
            pStmt.setInt(1, user_id);

    		// SQL文を実行
    		ResultSet rs = pStmt.executeQuery();

    		// 退避エリア
    		String save_order_date = null;
    		String order_date = null;

    		while (rs.next()) {
    			int id = rs.getInt("id");
//    			int user_id = rs.getInt("user_id");
    			int item_id = rs.getInt("item_id");
    			int item_price = rs.getInt("item_price");
    			int order_num = rs.getInt("order_num");
    			String item_name = rs.getString("item_name");
    			int sum_price = rs.getInt("sum_price");

    			// 注文単位ごとに注文履歴を区切って表示するため、
    			// 2行目以降は注文日時を空にする
    			order_date = rs.getString("order_date").substring(0, 16);
    			if(save_order_date == null) {
    				System.out.println("最初の注文日時=" + order_date);
					save_order_date = order_date;
					System.out.println("退避エリアに保持 save_order_date=" + save_order_date);
    			} else {

    				if(order_date.equals(save_order_date)) { //文字列はequalsメソッドで比較する
    					System.out.println("前回と同じ注文日時=" + order_date);
    					order_date = "";
    					System.out.println("order_dateにスペース order_date=" + order_date);
    				} else {
    					System.out.println("前回と異なる注文日時=" + order_date);
    					save_order_date = order_date;
    					System.out.println("退避エリアに保持 save_order_date=" + save_order_date);
    				}

    			}

				HistoryBean history = new HistoryBean(id, user_id, item_id, item_price, order_num, order_date, item_name, sum_price);
				historyList.add(history);
			}
    		System.out.println("DBから注文履歴を取得");

        } catch (SQLException e) {
        	e.printStackTrace();
        	return null;
        }
        System.out.println("........................................");
        return historyList;
    }

    public Boolean addHistory(int user_id, Map<Integer, List<Object>> cartMap) {
 		System.out.println("....................HistoryDAO(addHistory())....................");

 		Connection con = null;
        con = DBconnect.getConnection();

        try {
        	 // カートの情報を分解し、処理に使用できる形にする
        	 for (Object key : cartMap.keySet()) {
        		 System.out.println(key + " => " + cartMap.get(key));
        		 int item_id = (int)key;
        		 int item_price = (int) cartMap.get(key).get(1);
        		 int order_num = (int) cartMap.get(key).get(2);

	       		// ■注文履歴を追加する
	     		String sql = "INSERT INTO history(user_id, item_id, item_price, order_num) VALUES (?,?,?,?)";

	     		System.out.println("INSERT INTO history(user_id, item_id, item_price, order_num) VALUES (" + user_id + ", " + item_id + ", " + item_price + ", " + order_num + ")");

	            PreparedStatement pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, user_id);
	            pstmt.setInt(2, item_id);
	            pstmt.setInt(3, item_price);
	            pstmt.setInt(4, order_num);

	            int result = pstmt.executeUpdate();

	    		if (result == 1) {
	    			System.out.println("DBに1レコード追加");
	    		} else {
	    			System.out.println("DBレコード追加エラー");
	    		}
        	 }

        } catch (SQLException e) {
	         	e.printStackTrace();
	         	return false;
	    }
 		System.out.println("........................................");
 		return true;
    }
}