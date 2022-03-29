package com.dvduy.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dvduy.model.NewsModel;
import com.dvduy.service.ICategoryService;
import com.dvduy.service.INewService;

@WebServlet(urlPatterns = {"/trang-chu"})
public class HomeController extends HttpServlet{
	
	@Inject
	private ICategoryService categoryService;

	@Inject
	private INewService newService;


	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = "bai viet 4";
		String content = "bai viet 4";
		Long categoryId = 1L;
		NewsModel news = new NewsModel(title, content, categoryId); // create new news
		newService.save(news); // save new news
//		newService.update(news);
		req.setAttribute("categories", categoryService.findAll());
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/web/home.jsp");
		requestDispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
