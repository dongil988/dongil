package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		boolean go = false;
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
	
		PersistenceManager pm = MyPersistenceManager.getManager();
		
		Query qry = pm.newQuery(UserAccount.class);
		
		List<UserAccount> List = (List<UserAccount>) qry.execute();
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		
		for(UserAccount ua:List)
		{
			if(id.equals(ua.getUserID()))
			{
				go = true;
			}
		}
		if(go==true)
		{
			resp.getWriter().println("아이디가 중복");
		}
		else
		{
			UserAccount aa = new UserAccount(id,password,name);
			pm.makePersistent(aa);
			resp.getWriter().println("회원가입 완료");
		}
	
		resp.getWriter().println("</html>");
		resp.getWriter().println("</body>");
		
	}

}
