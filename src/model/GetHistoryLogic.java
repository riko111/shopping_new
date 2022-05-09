package model;

import java.util.List;

import dao.HistoryDAO;

public class GetHistoryLogic {
	public List<HistoryBean> execute(UserBean loginUser) {
		System.out.println("--------------------GetHistoryLogic(execute(loginUser))--------------------");

		int user_id = loginUser.getId();
		HistoryDAO dao = new HistoryDAO();
		List<HistoryBean> historyList = dao.getHistory(user_id);
		return historyList;
	}
}