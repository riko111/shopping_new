package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/HistoryServlet", "/CartServlet"}) //フィルタを設定するサーブレットクラス・JSPファイルを指定
public class LoginCheckFilter implements Filter {

	// ■フィルタがインスタンス化された直後
	public void init(FilterConfig fConfig) throws ServletException {}

	// ■設定したサーブレットクラスをリクエストしたとき
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("--------------------LoginCheckFilter（doFilter()）--------------------");
		System.out.println("ログインチェック");
		// ★前処理
        HttpSession session = ((HttpServletRequest)request).getSession(false); // セッションが存在しない場合NULLを返す
        RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginServlet");

        // セッションが何か存在する場合
        if(session != null){
        	Object loginCheck = session.getAttribute("loginUser");
        	// ログイン済み →通常どおりの遷移
        	if (loginCheck != null) {
        		chain.doFilter(request, response);

    	// ★後処理
    		// ログイン未 →ログイン画面へ飛ばす
        	} else {
        		dispatcher.forward(request, response);
        	}

		// セッションが何も存在しない場合 →ログイン画面へ飛ばす
        } else {
            dispatcher.forward(request, response);
        }
	}

	// ■フィルタのインスタンスが破棄される直前
	public void destroy() {}


}
