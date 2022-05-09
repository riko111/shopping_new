package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("--------------------CartServlet(doPost())--------------------");

		request.setCharacterEncoding("UTF-8");
		int item_id = Integer.parseInt(request.getParameter("item_id"));
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int sum_price = price * quantity;

		System.out.println("item_id=" + item_id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", sum_price=" + sum_price);

		HttpSession session = request.getSession();
		String cartId = (String) session.getAttribute("cartId");

		// カート情報を保持するcartMap
		// 挿入順を保持するためLinkedHashMapを使う
		Map<Integer, List<Object>> cartMap = new LinkedHashMap<>();
		// Mapの中にListがネストした構造
		List<Object> item = new ArrayList<>();

		if (session.getAttribute(cartId) == null) {
			// cartセッションが存在しない（初めてカートに入れる）場合
			System.out.println("cartセッションが存在しない");

			item.add(name);
			item.add(price);
			item.add(quantity);
			item.add(sum_price);

		} else {
			// cartセッションが存在する場合
			// cartMapを取得
			cartMap = (Map<Integer, List<Object>>) session.getAttribute(cartId);
			System.out.println("cartセッションが存在する");

			System.out.println("格納されているキーの一覧");
			System.out.println(cartMap.keySet());

			if (cartMap.containsKey(item_id)) {
				// 既に同じ商品がカートにある場合→更新
				System.out.println("既に同じ商品がカートにある");
				item = (List<Object>) cartMap.get(item_id);

				System.out.println("item.get(2)=" + item.get(2));
				System.out.println("item.get(3)=" + item.get(3));

				item.set(2, (int)item.get(2) + quantity); //個数（quantity）
				item.set(3, (int)item.get(3) + sum_price); //合計（sum_price）
			} else {
				// まだ同じ商品がカートにない場合→追加
				System.out.println("まだ同じ商品がカートにない");
	//			list.add(item_id);
				item.add(name);
				item.add(price);
				item.add(quantity);
				item.add(sum_price);
			}
		}

		cartMap.put(item_id, item);

		System.out.println("格納されているキーの一覧");
		System.out.println(cartMap.keySet());

		System.out.println(item + "をcartMap(" + item_id + ")に追加");
		System.out.println("cartMap" + cartMap);

		session.setAttribute(cartId, cartMap); //セッションスコープの属性名はユーザー固有

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
		dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("--------------------CartServlet(doGet())--------------------");

	    System.out.println("リンクから遷移");



	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
		dispatcher.forward(request, response);
	}

}
