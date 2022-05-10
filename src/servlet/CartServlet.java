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

import model.UserBean;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("--------------------CartServlet(doPost())--------------------");

	    request.setCharacterEncoding("UTF-8");
	    String action = request.getParameter("action");
	    int item_id = Integer.parseInt(request.getParameter("item_id"));

		// ■カート情報を保持するcartMap（Mapの中にListがネストした構造）
	    // ※挿入順を保持したい→LinkedHashMap
		Map<Integer, List<Object>> cartMap = new LinkedHashMap<>();
		List<Object> item = new ArrayList<>();

		HttpSession session = request.getSession();
		String cartId = (String) session.getAttribute("cartId");
		UserBean loginUser = (UserBean) session.getAttribute("loginUser");

		System.out.println("cartId=" + cartId);

		// ■「削除」ボタンが押された場合
	    if (action != null && action.equals("delete")) {

	    	cartMap = (Map<Integer, List<Object>>) session.getAttribute(cartId);
	    	cartMap.remove(item_id);
	    	System.out.println(item_id + "をカートから削除した");

	    // ■「カートに入れるボタン」が押された場合
	    } else {
	//	    	int item_id = Integer.parseInt(request.getParameter("item_id"));
			String name = request.getParameter("name");
			int price = Integer.parseInt(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));

			System.out.println("item_id=" + item_id + ", name=" + name + ", price=" + price + ", quantity=" + quantity);

			// ■cartセッションが存在しない（初めてカートに入れる）場合
			if (session.getAttribute(cartId) == null) {
				System.out.println("cartセッションが存在しない");

				item.add(name);
				item.add(price);
				item.add(quantity);
				item.add(price * quantity);

			// ■cartセッションが存在する場合
			} else {
				// cartMapを取得
				cartMap = (Map<Integer, List<Object>>) session.getAttribute(cartId);
				System.out.println("cartセッションが存在する");

				System.out.println("格納されているキーの一覧");
				System.out.println(cartMap.keySet());

				// ■既に同じ商品がカートにある場合→更新
				if (cartMap.containsKey(item_id)) {
					System.out.println("既に同じ商品がカートにある");
					item = (List<Object>) cartMap.get(item_id);

					System.out.println("item.get(2)=" + item.get(2));
					System.out.println("item.get(3)=" + item.get(3));
					quantity = (int)item.get(2) + quantity;
					item.set(2, quantity); //個数（quantity）
					item.set(3, price * quantity); //合計（sum_price）
					System.out.println("カートの " + item_id + " を更新");

				// ■まだ同じ商品がカートにない場合→追加
				} else {
					System.out.println("まだ同じ商品がカートにない");
		//			list.add(item_id);
					item.add(name);
					item.add(price);
					item.add(quantity);
					item.add(price * quantity);
					System.out.println(item_id + " をカートに追加");
				}
			}

			cartMap.put(item_id, item);
	    }

//		System.out.println("格納されているキーの一覧 " + cartMap.keySet());
		System.out.println("cartMap" + cartMap);

		// ■カートをセッション保存
		session.setAttribute(cartId, cartMap); //cartMapセッションの属性名はユーザー固有

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
