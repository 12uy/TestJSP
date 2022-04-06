package com.dvduy.controller.admin.api;

import com.dvduy.model.NewsModel;
import com.dvduy.model.UserModel;
import com.dvduy.service.INewsService;
import com.dvduy.utils.HttpUtil;
import com.dvduy.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Inject
    private INewsService newService;


	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        NewsModel newsModel = HttpUtil.of(req.getReader()).toModel(NewsModel.class); ////convert json to model
        newsModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(req,"USERMODEL")).getUserName());
        newsModel = newService.save(newsModel);
        mapper.writeValue(resp.getOutputStream(), newsModel);


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        NewsModel updateNews = HttpUtil.of(req.getReader()).toModel(NewsModel.class); ////convert json to model
        updateNews.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(req,"USERMODEL")).getUserName());
        updateNews = newService.update(updateNews);
        mapper.writeValue(resp.getOutputStream(), updateNews);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        NewsModel deleteNews = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
        newService.delete(deleteNews.getIds());
        mapper.writeValue(resp.getOutputStream(), "delete success");
    }

}
