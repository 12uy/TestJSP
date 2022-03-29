package com.dvduy.controller.admin.api;

import com.dvduy.model.NewsModel;
import com.dvduy.service.INewService;
import com.dvduy.utils.HttpUtil;

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
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print("NewAPI");
        NewsModel newsModel = HttpUtil.of(req.getReader()).toModel(NewsModel.class); ////convert json to model
        newService.save(newsModel);
        out.print(newsModel.toString());

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
