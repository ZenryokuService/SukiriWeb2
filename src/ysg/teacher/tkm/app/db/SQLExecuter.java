package ysg.teacher.tkm.app.db;

import java.util.Scanner;

public class SQLExecuter {
	public static void main(String[] args) {
		H2dbManager main = H2dbManager.getInstance();
		Scanner scan = new Scanner(System.in);

		System.out.println("SQLを実行します。");
		while(true) {
			String sql = scan.nextLine();
			if (sql == null || "".equals(sql)) {
				System.out.println("SQLを入力してください.");
				continue;
			}
			if ("bye".equals(sql)) {
				System.out.println("SQLExecuterを終了します.");
				break;
			} else if (sql.startsWith("select")) {
				main.executeQuery(sql);
			} else {
				main.execute(sql);
			}
		}
	}
}
