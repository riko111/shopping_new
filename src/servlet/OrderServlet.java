package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ChangeItemLogic;
import model.GetHistoryLogic;
import model.HistoryBean;
import model.ItemBean;
import model.UserBean;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("--------------------OrderServlet(doGet())--------------------");

		HttpSession session = request.getSession();
		String cartId = (String) session.getAttribute("cartId");
		Map<Integer, List<Object>> cartMap = (Map<Integer, List<Object>>) session.getAttribute(cartId);

		ChangeItemLogic changeItemLogic = new ChangeItemLogic();
		RequestDispatcher dispatcher = null;

		System.out.println("カート=" + cartMap);

		// カートの情報を分解し、処理に使用できる形にする
		for (Object key : cartMap.keySet()) {
			System.out.println(key + " => " + cartMap.get(key)); //要素の指定位置は0始まり
			int item_id = (int)key;
//			int price = (int) cartMap.get(key).get(1);
			int quantity = (int) cartMap.get(key).get(2);

			System.out.println("quantity=" + quantity);

			// ■商品テーブルの在庫数を変更
			// 必要なパラ（商品ID、注文数）
	//		String result = changeItemLogic.updateStock(cartMap);

			// ■在庫のチェック
			String short_stock = changeItemLogic.checkStock(item_id, quantity);

			// チェックOK
			if (short_stock == null) {
				System.out.println("在庫チェックOK");

			// チェックNG
			} else {
				List<ItemBean> itemList = (List<ItemBean>) session.getAttribute("itemList");

				System.out.println("item_id=" + item_id + "の在庫チェックNG");
				request.setAttribute("errorMsg", short_stock + "の在庫が足りないため、注文処理を完了できませんでした。");

			    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
				dispatcher.forward(request, response);
				return; //エラー（レスポンスをコミットした後でフォワードできません）対策
			}

		}

		boolean result = false;

		// カートの情報を分解し、処理に使用できる形にする
		for (Object key : cartMap.keySet()) {
			System.out.println(key + " => " + cartMap.get(key));
			int item_id = (int)key;
//			int price = (int) cartMap.get(key).get(1);
			int quantity = (int) cartMap.get(key).get(2);

			// ■在庫数の減算
			result = false;
			result = changeItemLogic.reduceStock(item_id, quantity);

			if (result) {
				System.out.println("在庫数の減算OK");

			} else {
				System.out.println("在庫数の減算NG");
				request.setAttribute("errorMsg", "申し訳ありません。エラーが発生したため、注文処理を完了できませんでした。");

				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
				dispatcher.forward(request, response);
				return; //エラー（レスポンスをコミットした後でフォワードできません）対策
			}

		}


		// OKなら次処理へ

		result = false;
		// カートの情報を分解し、処理に使用できる形にする
		for (Object key : cartMap.keySet()) {
			System.out.println(key + " => " + cartMap.get(key));
			int item_id = (int)key;
			int price = (int) cartMap.get(key).get(1);
			int quantity = (int) cartMap.get(key).get(2);

			// ■注文履歴テーブルに追加
			UserBean loginUser = (UserBean) session.getAttribute("loginUser");
			int user_id = loginUser.getId();

			// 必要なパラ（ユーザーID、商品ID、単価、注文数）
			result = changeItemLogic.addHistory(user_id, item_id, price, quantity);

			if (result) {
				System.out.println("注文履歴の追加OK");

				GetHistoryLogic getHistoryLogic = new GetHistoryLogic();
				List<HistoryBean> historyList = getHistoryLogic.execute(loginUser);

				//注文履歴のセッションを更新
				session.setAttribute("historyList", historyList);

			}

		}

		// OKなら次処理へ

		// ■注文処理完了後、カートを空にする
    	session.removeAttribute("cartMap");
    	session.removeAttribute(cartId);
    	System.out.println("カートを空にした");

		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
	}

}
