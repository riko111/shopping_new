package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBconnect;
import model.ItemBean;

public class ItemDAO {
    public List<ItemBean> getItem() {
		System.out.println("....................ItemDAO(getItem())....................");

		List<ItemBean> itemList = new ArrayList<>();

		// データベース接続
		Connection con = null;
        con = DBconnect.getConnection();

        try {
    		String sql = "SELECT id, name, type, price, quantity, image, state, created_at "
    				+ "FROM item ORDER BY id DESC";

    		System.out.println(sql);

            PreparedStatement pstmt = con.prepareStatement(sql);
//            pStmt.setInt(1, type);

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

        } catch (SQLException e) {
        	e.printStackTrace();
        	return null;
        }
        System.out.println("........................................");
        return itemList;
    }


	// ◆在庫数をチェックするメソッド
	public int checkStock(int item_id, int quantity) {
 		System.out.println("....................ItemDAO(checkStock())....................");

 		// データベース接続
 		Connection con = null;
         con = DBconnect.getConnection();

         try {
        	// ■在庫数のチェック
        	 String sql0 = "SELECT "
			        	 + "CASE WHEN quantity<" + quantity +  " THEN false "
			        	 + "ELSE true "
			        	 + "END AS is_orderable "
			        	 + "FROM item WHERE id=" + item_id; //※パラメータ指定すると文法エラーになったため、値を直接記述した

        	System.out.println("SELECT CASE WHEN quantity<" + quantity + " THEN false ELSE true END AS is_orderable FROM item WHERE id=" + item_id);

        	PreparedStatement pstmt0 = con.prepareStatement(sql0);
       		ResultSet rs = pstmt0.executeQuery(sql0);
//
       		boolean is_orderable = false;
       		if (rs.next()) {
       			is_orderable = rs.getBoolean(1);
       			System.out.println("is_orderable=" + is_orderable);
       		}

       		if (!is_orderable) {
       			System.out.println("在庫が不足");
       			System.out.println("........................................");
       			return item_id; //不足商品のitem_id
       		}

         } catch (SQLException e) {
          	e.printStackTrace();
//  			return false;
         }

         System.out.println("在庫が足りている");
         System.out.println("........................................");
         return 0; //問題なし
}


    public boolean reduceStock(int item_id, int quantity) {
 		System.out.println("....................ItemDAO(adjustStock())....................");

 		// データベース接続
 		Connection con = null;
         con = DBconnect.getConnection();

         int orderResult = 0;

         try {
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

         } catch (SQLException e) {
         	e.printStackTrace();
// 			return false;

         }
         System.out.println("在庫数を注文数分減らした");
         System.out.println("........................................");
         return true;
     }

}