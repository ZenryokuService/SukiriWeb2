package sukiri;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ysg.teacher.tkm.app.db.H2dbManager;

public class HelloServlet2 extends HttpServlet {

	/** GETリクエスト */
	@Override
	protected void doGet(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		String message = "Hello World";
		String uri = request.getRequestURI();
		System.out.println(message);

		H2dbManager dao = H2dbManager.getInstance();
		List<String> list = new ArrayList<>();
		dao.executeQuery("SELECT * FROM TEST;", list);

		StringBuilder bb = new StringBuilder();
		bb.append("<html>");
		for (String s : list) {
			bb.append(s + "<br/>");
		}
		bb.append("</html>");

		response.getWriter().append(bb.toString());
	}

	/** POSTリクエスト */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
