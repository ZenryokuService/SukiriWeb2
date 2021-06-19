package sukiri;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sukiri.data.MyData;
import ysg.teacher.tkm.app.db.H2dbManager;

@WebServlet("/world")
public class HelloServlet extends HttpServlet {

	/** GETリクエスト */
	@Override
	protected void doGet(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		String message = "Hello World";
		String uri = request.getRequestURI();
		System.out.println(getServletContext().getRealPath("/"));

		System.out.println("start");
		H2dbManager dao = H2dbManager.getInstance();
		List<String> list = new ArrayList<>();
		dao.executeQuery("SELECT * FROM TEST;", list);
		System.out.println("end");

		StringBuilder bb = new StringBuilder();
		bb.append("<html>");
		bb.append("<head>");
		bb.append("<title>ハローサーブレット</title>");
		bb.append("</head>");
		bb.append("<body>");
		bb.append("DBから取得したデータ</br>");
		for (String s : list) {
			bb.append(s + "<br/>");
		}
		// 占いの結果
		String[] keka = new String[] {"長スッキリ", "スッキリ", "最悪 "};
		int idx = (int)(Math.random() * 3);
		bb.append(keka[idx]);
		bb.append(request.getParameter("ans"));
		bb.append("<form action='/SukiriWeb2/hello' method='post'>");
		bb.append("<input type=\"text\" size=20 name=\"ans1\"/>");
		bb.append("<input type=\"submit\" value=\"サーバーへ送信\"/>");
		bb.append("</body>");
		bb.append("</html>");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.append(bb.toString());
	}

	/** POSTリクエスト */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("*** POST ***");
		MyData data = new MyData();
		data.setLeft(Integer.parseInt(request.getParameter("left")));
		data.setRight(Integer.parseInt(request.getParameter("right")));
		data.setAns(Integer.parseInt(request.getParameter("ans")));
		doGet(request, response);
		PrintWriter out = response.getWriter();
		out.append("MyData: " + data.getRight());
//		response.set
	}
}
