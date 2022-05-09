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

import model.GetHistoryLogic;
import model.HistoryBean;
import model.UserBean;

@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("--------------------HistoryServlet(doGet())--------------------");

	    HttpSession session = request.getSession();

		if(session.getAttribute("historyList") == null) {
			// 注文履歴を未取得（初回）の場合
			UserBean loginUser = (UserBean) session.getAttribute("loginUser");

			GetHistoryLogic getHistoryLogic = new GetHistoryLogic();
			List<HistoryBean> historyList = getHistoryLogic.execute(loginUser);

			//セッションスコープに保存
			session.setAttribute("historyList", historyList);
		}

		System.out.println("リンクから注文履歴に遷移");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/history.jsp");
		dispatcher.forward(request, response);
	}

}
