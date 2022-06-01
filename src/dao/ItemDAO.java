package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.DBconnect;
import model.ItemBean;

public class ItemDAO {

	// ◆商品一覧を取得するメソッド
    public List<ItemBean> getItem(int userId) {
		System.out.println("....................ItemDAO(getItem())....................");

		List<ItemBean> itemList = new ArrayList<>();

		// データベース接続
		Connection con = null;
        con = DBconnect.getConnection();

		String sql = null;
		// 管理者の場合、全ての商品を取得
		if (userId == 1) {
			sql = "SELECT id, name, type, price, quantity, image, state, created_at "
					+ "FROM item ORDER BY id DESC";

		// 一般ユーザーの場合、販売中商品のみを取得
		} else {
			sql = "SELECT id, name, type, price, quantity, image, state, created_at "
					+ "FROM item WHERE state=1 ORDER BY id DESC"; //state=0（販売中止）,state=1（販売中）
		}

        try {
        	System.out.println(sql);
            PreparedStatement pstmt = con.prepareStatement(sql);

    		// SQL文を実行
    		ResultSet rs = pstmt.executeQuery();

    		while (rs.next()) {

    			int id = rs.getInt("id");
    			String name = rs.getString("name");
    			String type = rs.getString("type");
    			int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				String image = rs.getString("image");
				int state = rs.getInt("state");
				String created_at = rs.getString("created_at");

				ItemBean item = new ItemBean(id, name, type, price, quantity, image, state, created_at);
				itemList.add(item);
			}
    		System.out.println("DBから商品一覧を取得");
    		System.out.println("itemList=");

            for(ItemBean item : itemList) {
                System.out.println("(" + item.toString() + ")");
            }


        } catch (SQLException e) {
        	e.printStackTrace();
        	return null;
        }
        System.out.println("........................................");
        return itemList;
    }


	// ◆在庫数をチェックするメソッド
	public String checkStock(Map<Integer, List<Object>> cartMap) {
 		System.out.println("....................ItemDAO(checkStock())....................");

 		// データベース接続
 		Connection con = null;
 		con = DBconnect.getConnection();

 		String short_stock = null; //在庫不足がある場合その商品名

        try {
        	// カートの情報を分解し、処理に使用できる形にする
        	for (Object key : cartMap.keySet()) {
        		int item_id = (int)key;
        		int quantity = (int) cartMap.get(key).get(2);

        		System.out.println("quantity=" + quantity);

	        	// ■在庫数のチェック
	        	String sql0 = "SELECT name, "
				        	 + "CASE WHEN quantity<" + quantity +  " THEN false "
				        	 + "ELSE true "
				        	 + "END AS is_orderable "
				        	 + "FROM item WHERE id=" + item_id; //※パラメータ指定すると文法エラーになったため、値を直接記述した

	        	System.out.println("SELECT name, CASE WHEN quantity<" + quantity + " THEN false ELSE true END AS is_orderable FROM item WHERE id=" + item_id);

	        	PreparedStatement pstmt0 = con.prepareStatement(sql0);
	       		ResultSet rs = pstmt0.executeQuery(sql0);

	       		boolean is_orderable = false;
	       		if (rs.next()) {
	       			is_orderable = rs.getBoolean("is_orderable");
	       			System.out.println("is_orderable=" + is_orderable);
	       		}

	       		if (!is_orderable) {
	       			System.out.println("item_id=" + item_id + "の在庫が不足");
	       			short_stock = rs.getString("name");
	       			System.out.println("........................................");
	       			return short_stock; //在庫不足があればブロックを抜ける
	       		}

        	}
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		System.out.println("在庫が足りている");
		System.out.println("........................................");
		return short_stock;
}


    public boolean reduceStock(Map<Integer, List<Object>> cartMap) {
 		System.out.println("....................ItemDAO(adjustStock())....................");

 		// データベース接続
 		Connection con = null;
        con = DBconnect.getConnection();

        try {
        	 for (Object key : cartMap.keySet()) {
        		 int item_id = (int)key;
        		 int quantity = (int) cartMap.get(key).get(2);

        		 System.out.println("quantity=" + quantity);

	       		// ■在庫数を減らす
	     		String sql = "UPDATE item SET quantity=quantity-? WHERE id=?";

	     		System.out.println("UPDATE item SET quantity=quantity-" + quantity + " WHERE id=" + item_id);

	             PreparedStatement pstmt = con.prepareStatement(sql);
	             pstmt.setInt(1, quantity);
	             pstmt.setInt(2, item_id);

	     		// SQL文を実行
	     		int result = pstmt.executeUpdate();

	     		if (result != 1) {
	     			System.out.println("更新エラー");
	     			return false;
	     		}

        	 }
         } catch (SQLException e) {
        	 e.printStackTrace();
//        	 return false;
         }

         System.out.println("在庫を注文数分減らした");
         System.out.println("........................................");
         return true;
     }

	// ◆在庫を初期設定するメソッド（管理者用）
	public boolean restoreItemList() {
 		System.out.println("....................ItemDAO(restoreItemList())....................");

 		System.out.println("在庫の初期設定");

 		// データベース接続
 		Connection con = null;
 		con = DBconnect.getConnection();

 		boolean result = true;
        try {
        	String sql = "UPDATE item SET quantity=? WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            int[] stocks = {5, 1, 5, 5, 5, 5, 5};

            for (int i=0; i<stocks.length; i++) {
	            pstmt.setInt(1, stocks[i]);
	            pstmt.setInt(2, i+1);

	            System.out.println("UPDATE item SET quantity=" + stocks[i] + " WHERE id=" + (i+1));
	            int r = pstmt.executeUpdate();
	            if (r != 1) {
	            	result = false;
	            }
            }

        } catch (SQLException e) {
       	 e.printStackTrace();
        }
        System.out.println("........................................");
        return result;
    }
}