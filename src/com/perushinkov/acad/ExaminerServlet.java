package com.perushinkov.acad;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ExaminerServlet
 */
@WebServlet("/ExaminerServlet")
public class ExaminerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static SessionManager manager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExaminerServlet() {
        super();


        manager = new SessionManager();
       
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSess = request.getSession();

		if (manager.getDataManager() == null) {
			String filename = "/WEB-INF" ;
			ServletContext context = this.getServletContext();
			String pathname =context.getRealPath(filename);
			
			manager.setDataManager(new DB(pathname));
		}
	

		
		UserSession user = manager.getSession(httpSess);
		if (user == null) {
			user = manager.createSession(httpSess);
		}

		try{
			user.respond(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
