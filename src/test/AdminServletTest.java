package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDAO;

@WebServlet("/AdminServletTest")
public class AdminServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ItemDAO dao = new ItemDAO();
		boolean result = dao.restoreItemList();
		if (result) {
			request.setAttribute("errorMsg", "実行完了");
		} else {
			request.setAttribute("errorMsg", "実行エラー");

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
		dispatcher.forward(request, response);
	}

}
