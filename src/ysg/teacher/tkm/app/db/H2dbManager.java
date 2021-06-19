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

import sukiri.data.LoginData;

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
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/file:C:\\pleiades-2020-03-java-win-64bit-jre_20200322\\pleiades\\workspace\\SukiriWeb2\\WebContent\\WEB-INF\\database");
			Statement stmt = con.createStatement();
			stmt.execute(SQLConst.CREATE_USER_TBL);
			stmt.execute(SQLConst.CREATE_ITEM_TBL);
			stmt.execute(SQLConst.CREATE_ITEM_TYPE_TBL);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (Exception e) {
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
		if (con == null) {
			return;
		}

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			con = null;
			instance = null;
		}
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
	 * SELECT文を実行する。表示するだけ。
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
	 * SELECT文を実行する。表示するだけ。
	 *
	 * @param sql SELECT文
	 * @param key 対象のキーになる値
	 * @return 成功：
	 */
	public LoginData selectUser(String userName, String passwd) {
		ResultSet result = null;
		LoginData data = null;

		StringBuilder build = new StringBuilder();
		try {
			PreparedStatement stmt = con.prepareStatement(SQLConst.SELECT_USER_FOR_LOGIN);
			stmt.setString(1, userName);
			stmt.setString(2, passwd);
			result = stmt.executeQuery();
			ResultSetMetaData meta = result.getMetaData();
			int count = meta.getColumnCount();
			for (int i = 1; i <= count; i++) {
				build.append(meta.getColumnName(i) + ",");
			}
			String header = build.toString().substring(0, build.length() -1);
			System.out.println(header);
			StringBuilder datas = new StringBuilder();
			if (result.next()) {
				data = new LoginData(result.getString(2), result.getInt(4));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return data;
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

	public PreparedStatement createPreparedStatement(String sql) throws SQLException {
		// プリペアードステートメント
		return con.prepareStatement(sql);
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
