package test;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

public class CartServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

	}

	public static void deleteCart(String cartId) {
		HttpSession session = request.getSession();

		session.removeAttribute("loginUser");
	}


}
