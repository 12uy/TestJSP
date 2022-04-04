package com.dvduy.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dvduy.model.NewsModel;
import com.dvduy.model.UserModel;
import com.dvduy.service.ICategoryService;
import com.dvduy.service.INewsService;
import com.dvduy.service.IUserService;
import com.dvduy.utils.FormUtil;
import com.dvduy.utils.SessionUtil;

@WebServlet(urlPatterns = {"/trang-chu", "/login","/logout"})
public class HomeController extends HttpServlet{
	
	@Inject
	private ICategoryService categoryService;

	@Inject
	private INewsService newService;

	@Inject
	private IUserService userService;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
		String action = req.getParameter("action");
		if(action != null && action.equals("login")) {
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if (message != null && alert != null) {
				req.setAttribute("message", resourceBundle.getString(message));
				req.setAttribute("alert", "danger");
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/login.jsp");
			dispatcher.forward(req, resp);
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(req, "USERMODEL");
			resp.sendRedirect(req.getContextPath() + "/trang-chu");
		} else {
			req.setAttribute("categories", categoryService.findAll());
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/web/home.jsp");
			requestDispatcher.forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action != null && action.equals("login")) {
			UserModel userModel = FormUtil.toModel(UserModel.class, req);
			userModel = userService.findAccount(userModel.getUserName(), userModel.getPassword(), 1);
			if(userModel != null) {
				SessionUtil.getInstance().putValue(req, "USERMODEL", userModel);
				if (userModel.getRole().getCode().equals("ADMIN")) {
					resp.sendRedirect(req.getContextPath() + "/admin-home");
				}
				if (userModel.getRole().getCode().equals("USER")) {
					resp.sendRedirect(req.getContextPath() + "/trang-chu");
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/login?action=login&message=username_or_password_invalid&alert=danger");
			}
		}
	}

}
