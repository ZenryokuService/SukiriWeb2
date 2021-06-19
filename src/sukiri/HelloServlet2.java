package sukiri;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sukiri.data.MyData;

@WebServlet("/hello2")
public class HelloServlet2 extends HttpServlet {

	/** GETリクエスト */
	@Override
	protected void doGet(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		String message = "Hello World";
		String uri = request.getRequestURI();
		System.out.println(message);

		StringBuilder bb = new StringBuilder();
		bb.append("<html>");
		bb.append(message + "<br/>");
		bb.append("</html>");

		response.getWriter().append(bb.toString());
	}

	/** POSTリクエスト */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		String res = "aaaaaAAA";
		String param = request.getParameter("ans");

		if (param != null && param.matches("0-9")) {
			response.sendRedirect("/SukiriWeb2/First.jsp");
		} else {
			RequestDispatcher d = request.getRequestDispatcher("/SukiriWeb2/Hello.jsp");
			request.setAttribute("name", "太郎");
			request.setAttribute("age", 12);
			MyData data = new MyData();
			data.setName("次郎");
			data.setAge(11);
			request.setAttribute("data", data);
			d.forward(request, response);
		}
	}
}
