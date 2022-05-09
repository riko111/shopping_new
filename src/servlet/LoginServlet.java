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

		if(loginUser == null) {
			System.out.println("ログインNG");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginFailure.jsp");
			dispatcher.forward(request, response);

		}else{
			System.out.println("ログインOK");
			System.out.println("loginUserインスタンス(" + loginUser.getId() + ", " + loginUser.getUserName() + ", " +  loginUser.getPass() + ")");

			HttpSession session = request.getSession();

		    session.setAttribute("loginUser", loginUser);
		    System.out.println("セッションスコープにloginUserを保存");


			String cartId = "cart" + loginUser.getId();
			session.setAttribute("cartId", cartId);
			System.out.println("セッションスコープにcartIdを保存");
			System.out.println("cartId=" + cartId);

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

		RequestDispatcher dispatcher = null; //どうすべきか？

		if(action != null && action.equals("logout")) {
			System.out.println("action='logout'");
			HttpSession session = request.getSession();

//			session.invalidate(); //全てのセッションを破棄
			session.removeAttribute("loginUser");
			session.removeAttribute("itemList");
			session.removeAttribute("historyList");
			session.removeAttribute("cartId");
			System.out.println("ログアウトしました");
			System.out.println("セッションスコープ(loginUser)を破棄");

			//
			Map<Integer, List<Object>> cartMap = (Map<Integer, List<Object>>) session.getAttribute("cartMap");
			System.out.println("cartMapインスタンス=" + cartMap);
			//

			dispatcher = request.getRequestDispatcher("/");
		} else {
			System.out.println("action=''");
//		    List<ItemBean> itemList = null;
//		    //セッションスコープで渡す
//		    HttpSession session = request.getSession();
//		    session.setAttribute("itemList", itemList);

		    System.out.println("リンクから商品リストに遷移");

			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp");
		}

		dispatcher.forward(request, response);
	}

}
