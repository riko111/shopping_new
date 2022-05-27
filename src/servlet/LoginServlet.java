package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetItemListLogic;
import model.ItemBean;
import model.LoginLogic;
import model.UserBean;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("--------------------LoginServlet(doPost())--------------------");

	    request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String pass = request.getParameter("pass");
		System.out.println("userName=" + userName + ", pass=" + pass);

		LoginLogic loginLogic = new LoginLogic();
		UserBean loginUser = loginLogic.execute(userName, pass);

		// ■ログインしっぱい
		if (loginUser == null) {
			System.out.println("ログインNG");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./loginFailure.jsp");
			dispatcher.forward(request, response);

		// ■ログイン成功
		} else {
			System.out.println("ログインOK");
			System.out.println("loginUserインスタンス(" + loginUser.getId() + ", " + loginUser.getUserName() + ", " +  loginUser.getPass() + ")");

			HttpSession session = request.getSession();

		    session.setAttribute("loginUser", loginUser);
		    System.out.println("セッションスコープにloginUserを保存");

//		    // cartIdを準備
			String cartId = "" + loginUser.getId();
			session.setAttribute("cartId", cartId);
			System.out.println("セッションスコープにcartIdを保存");
			System.out.println("cartId=" + cartId);

			// 商品リストを取得
		    GetItemListLogic getItemListLogic = new GetItemListLogic();
		    List<ItemBean> itemList = getItemListLogic.execute();
		    //リクエストスコープで渡す
		    session.setAttribute("itemList", itemList);
		    System.out.println("セッションスコープにitemListを保存");

		    RequestDispatcher dispatcher;

		    // 管理者の場合
		    if (loginUser.getId() == 1) {
		    	dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
		    // 一般ユーザーの場合
		    } else {
		    	dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp");
		    }

			dispatcher.forward(request, response);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("--------------------LoginServlet(doGet())--------------------");

	    request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println("action=" + action);

		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;

		// ■ログアウト処理
		if (action != null && action.equals("logout")) {

			// カート以外のセッションを破棄
//			session.invalidate(); //全てのセッションを破棄
			session.removeAttribute("cartMap");

			session = request.getSession();

			session.removeAttribute("loginUser");
			session.removeAttribute("itemList");
			session.removeAttribute("historyList");
			session.removeAttribute("cartMap");
			System.out.println("ログアウトしました");
			System.out.println("セッションスコープ(loginUser, itemList, historyList)を破棄");
			System.out.println("セッションスコープ（cartMap）は、cartId（ユーザーID）で保持");

			response.sendRedirect("./");

		// ■リンクから商品リストに遷移
		} else if (action != null && action.equals("itemList")) {
			// 商品リストのセッションを最新化
		    GetItemListLogic getItemListLogic = new GetItemListLogic();
		    List<ItemBean> itemList = getItemListLogic.execute();

		    session.setAttribute("itemList", itemList);

		    System.out.println("リンクから商品リストに遷移");
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp");
			dispatcher.forward(request, response);

		// ■ログインチェックでエラー
		} else {
			System.out.println("不正ログインのためログイン失敗画面にリダイレクト");
			response.sendRedirect("./loginFailure.jsp"); //URL表示を考慮しリダイレクト、ファイルはWebContent直下なので注意
		}
	}

}
