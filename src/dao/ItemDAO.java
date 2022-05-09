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
//        	String sql = "SELECT id, name, type, price, quantity, image, state, created_at "
//        			+ "FROM item WHERE type=? ORDER BY id DESC";
    		String sql = "SELECT id, name, type, price, quantity, image, state, created_at "
    				+ "FROM item ORDER BY id DESC";

    		System.out.println("SELECT id, name, type, price, quantity, image, state, created_at "
    				+ "FROM item ORDER BY id DESC");

            PreparedStatement pStmt = con.prepareStatement(sql);
//            pStmt.setInt(1, type);

    		// SQL文を実行
    		ResultSet rs = pStmt.executeQuery();

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
}