package test;

import java.util.List;

import dao.HistoryDAO;
import model.HistoryBean;

public class GetHistoryLogicTest {
	public static void main(String[] args) {
		int user_id = 2; //この値を変更してテストする
		List<HistoryBean> historyList = testExecute(user_id);

		System.out.println("注文履歴を取得");
		for (HistoryBean history : historyList) {
//			System.out.println("historyインスタンス(" + history.getId() + ", " + history.getUser_id() + ", " + history.getItem_id() + ", " + history.getItem_price() + ", " + history.getOrder_num() + ", " + history.getOrder_date() + ")");
			System.out.println("historyインスタンス①(" + history.getId() + ", " + history.getUser_id() + ", " + history.getItem_id() + ", " + history.getItem_price() + ", " + history.getOrder_num() + ", " + history.getOrder_date() + ", " + history.getItem_name() + ", " + history.getSum_price() + ")");
		}
	}

	public static List<HistoryBean> testExecute(int user_id) {
		System.out.println("--------------------GetHistoryLogicTest(testExecute(user_id))--------------------");

		HistoryDAO dao = new HistoryDAO();
		return dao.getHistory(user_id);
	}

}
