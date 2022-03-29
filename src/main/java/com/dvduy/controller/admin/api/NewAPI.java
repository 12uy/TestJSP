package com.dvduy.controller.admin.api;

import com.dvduy.model.NewsModel;
import com.dvduy.service.INewService;
import com.dvduy.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Inject
    private INewService newService;


	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        NewsModel newsModel = HttpUtil.of(req.getReader()).toModel(NewsModel.class); ////convert json to model
        newsModel = newService.save(newsModel);
        mapper.writeValue(resp.getOutputStream(), newsModel);


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        NewsModel updateNews = HttpUtil.of(req.getReader()).toModel(NewsModel.class); ////convert json to model
        updateNews = newService.update(updateNews);
        mapper.writeValue(resp.getOutputStream(), updateNews);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        NewsModel deleteNews = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
        newService.delete(deleteNews.getIds());
        mapper.writeValue(resp.getOutputStream(), "delete success");
    }


}
