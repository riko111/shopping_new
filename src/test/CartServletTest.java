package test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

public class CartServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		int item_id = 1;
		String name = "シンプルネクタイ";
		int price = 2900;
		int quantity = 2;
		int sum_price = price * quantity;
		int user_id = 2;

		Map<Integer, List<Object>> cartMap = new LinkedHashMap<>();
		List<Object> list1 = new ArrayList<>();

		list1.add(item_id);
		list1.add(name);
		list1.add(price);
		list1.add(quantity);
		list1.add(sum_price);

		System.out.println(list1 + "（" + item_id + "）を" + "cartMapに追加");
		cartMap.put(item_id, list1);

		List<Object> list2 = new ArrayList<>();

		list2.add(2);
		list2.add("ワンカラーネクタイ");
		list2.add(2000);
		list2.add(3);
		list2.add(6000);
		cartMap.put(2, list2);

		System.out.println(list2 + "（" + item_id + "）を" + "cartMapに追加");
		cartMap.put(2, list2);


		HttpSession session = request.getSession();
		session.setAttribute(user_id, cartMap); //個別にカートを持たせるため、ユーザー名をセッション名に使用

		for(Integer key : cartMap.keySet()) {
			System.out.println("key=" + key);
			for(int i=1; i<5; i++) {
				System.out.println(cartMap.get(key).get(i));
			};

		}
	}

//	public static void add(int id, int quantity) {
//		Map<Integer, List<Object>> cartMap = new LinkedHashMap<>();
//		List<Object> list1 = new ArrayList<>();
//
//		int new_quantity = cartMap.get(id).get(4) + quantity;
//		cartMap.get(id).set(3, new_quantity);
//	}

}
