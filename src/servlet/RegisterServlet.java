package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RegisterLogic;
import model.UserBean;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("--------------------RegisterServlet(doGet())--------------------");

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("--------------------RegisterServlet(doPost())--------------------");

	    request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String pass = request.getParameter("pass");
		System.out.println("userName=" + userName + ", pass=" + pass);

		RequestDispatcher dispatcher;

		UserBean loginUser = null;
		RegisterLogic registerLogic = new RegisterLogic();

		boolean isValidInput = true;

		// ■入力チェック
		// ユーザー名のチェックNG
		if (!registerLogic.isValidInput(userName)) {
			request.setAttribute("errorMsg", "ユーザー名が不正です。指定された形式で入力してください。");
			isValidInput = false;
		// パスワードのチェックNG
		} else if (!registerLogic.isValidInput(pass)) {
			request.setAttribute("errorMsg", "パスワードが不正です。指定された形式で入力してください。");
			isValidInput = false;
		}

		// 入力チェックNGなら、アカウント登録しない
		if (!isValidInput) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			dispatcher.forward(request, response);
			return;
		}

	    // ■アカウント登録
		loginUser = registerLogic.execute(userName, pass);
		// 登録成功
		if(loginUser != null) {
			HttpSession session = request.getSession();
		    session.setAttribute("loginUser", loginUser);
		    System.out.println("アカウント登録OK");

		    request.setAttribute("errorMsg", "アカウント " + userName + " を新規登録しました。");

	    // 登録しっぱい
		}else{
			System.out.println("アカウント登録しっぱい");

			request.setAttribute("errorMsg", "登録できません。別のユーザー名を入力してください。");
		}

		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);

	}
}