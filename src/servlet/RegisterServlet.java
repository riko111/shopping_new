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

//	    RegisterLogic registerLogic = new RegisterLogic();
//	    registerLogic.execute()
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("--------------------RegisterServlet(doPost())--------------------");

	    request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String pass = request.getParameter("pass");
		System.out.println("userName=" + userName + ", pass=" + pass);

		RegisterLogic registerLogic = new RegisterLogic();
		UserBean loginUser = registerLogic.execute(userName, pass);

	    RequestDispatcher dispatcher;

		if(loginUser != null) {
			HttpSession session = request.getSession();
		    session.setAttribute("loginUser", loginUser);
		    System.out.println("アカウント登録OK");

	    	dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp");
		}else{
			System.out.println("アカウント登録しっぱい");
	    	dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");

		}

	}
}