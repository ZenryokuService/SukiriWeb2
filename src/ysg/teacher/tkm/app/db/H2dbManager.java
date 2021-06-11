package ysg.teacher.tkm.app.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * インストールしたH2DBの管理クラス(DAO)
 *
 * @author 作成者の名前
 *
 */
public class H2dbManager {
	/** このクラスのインスタンス */
	private static H2dbManager instance;
	/** DBコネクション */
	private Connection con;

	/**
	 * プライベートコンストラクタ、H2DBのコネクションを取得する。
	 * {@link H2dbManager#getInstance()}
	 */
	private H2dbManager() {
		try {

			con = DriverManager.getConnection("jdbc:h2:C:\\Users\\tkm-yogo\\Documents\\sampleCode\\database");
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery("select * from TEST;");
			result.next();
			String id = result.getString(1);
			String name = result.getString(2);
			System.out.println("ID: " + id + "Name: " + name);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * このクラスのインスタンスを取得する。
	 * @return H2dbManagerインスタンス
	 */
	public static H2dbManager getInstance() {
		if (instance == null) {
			instance = new H2dbManager();
		}
		return instance;
	}

	/**
	 * このクラスが解放されるときに起動する。
	 */
	@Override
	public void finalize() {

		con = null;
		instance = null;
	}

	/**
	 * SELECT文を実行する。
	 *
	 * @param sql SELECT文
	 * @return 成功：
	 */
	public List<String> executeQuery(String sql, List<String> list) {
		boolean isSuccess = false;
		ResultSet result = null;
		StringBuilder build = new StringBuilder();
		try {
			Statement stmt = con.createStatement();
			result = stmt.executeQuery(sql);
			ResultSetMetaData meta = result.getMetaData();
			int count = meta.getColumnCount();
			for (int i = 1; i <= count; i++) {
				build.append(meta.getColumnName(i) + ",");
			}
			String header = build.toString().substring(0, build.length() -1);
			System.out.println(header);
			StringBuilder datas = new StringBuilder();
			while (result.next()) {
				for (int i = 1; i <= count; i++) {
					list.add(result.getString(i));
					datas.append(result.getString(i) + ",");
				}
				String line = datas.toString().substring(0, datas.length() -1);
				System.out.println(line);
			}
			isSuccess = true;
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return list;
	}
	/**
	 * SELECT文を実行する。
	 *
	 * @param sql SELECT文
	 * @return 成功：
	 */
	public boolean executeQuery(String sql) {
		boolean isSuccess = false;
		ResultSet result = null;
		StringBuilder build = new StringBuilder();
		try {
			Statement stmt = con.createStatement();
			result = stmt.executeQuery(sql);
			ResultSetMetaData meta = result.getMetaData();
			int count = meta.getColumnCount();
			for (int i = 1; i <= count; i++) {
				build.append(meta.getColumnName(i) + ",");
			}
			String header = build.toString().substring(0, build.length() -1);
			System.out.println(header);
			StringBuilder datas = new StringBuilder();
			while (result.next()) {
				for (int i = 1; i <= count; i++) {
					datas.append(result.getString(i) + ",");
				}
				String line = datas.toString().substring(0, datas.length() -1);
				System.out.println(line);
			}
			isSuccess = true;
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * Create文などのSELECT以外のSQLを実行する。
	 * @param sql
	 */
	public boolean execute(String sql) {
		boolean isSuccess = false;
		try {
			con.createStatement().execute(sql);
			isSuccess = true;
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return isSuccess;
	}

	public boolean importCsv(Path path) throws IOException, SQLException {
		// プリペアードステートメント
		PreparedStatement prep = con.prepareStatement("insert into ITEMS (CETEGORY_ID,ITEM_NAME,MONEY,EFFECT_ID,EFFECT_VALUE,WEIGHT,NUMBER_OF_TIMES)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)");

		BufferedReader buf = Files.newBufferedReader(path);
		String line = null;

		// ヘッダーを飛ばす
		buf.readLine();
		while ((line = buf.readLine()) != null) {
			String[] data = line.split(",");
			System.out.println(line);
			for (int i = 0; i < data.length; i++) {
				switch (i) {
				case 0:
					prep.setString(i + 1, data[i]);
					break;
				case 1:
					prep.setString(i + 1, data[i]);
					break;
				case 2:
					prep.setInt(i + 1, Integer.parseInt(data[i]));
					break;
				case 3:
					prep.setString(i + 1, data[i]);
					break;
				case 4:
					prep.setInt(i + 1, Integer.parseInt(data[i]));
					break;
				case 5:
					prep.setInt(i + 1, Integer.parseInt(data[i]));
					break;
				case 6:
					prep.setInt(i + 1, Integer.parseInt(data[i]));
					break;
				default:
					throw new IOException("想定外の入力です。IOExceptionfではなくユーザー定義エクセプションを使用するべき");
				}
			}
			if (prep.executeUpdate() == 0) {
				throw new SQLException("INSERTエラーです。");
			}
		}
		return true;
	}
}
