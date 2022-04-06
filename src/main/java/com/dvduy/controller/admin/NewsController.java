package com.dvduy.controller.admin;

import com.dvduy.model.NewsModel;
import com.dvduy.paging.PageRequest;
import com.dvduy.paging.Pageble;
import com.dvduy.service.ICategoryService;
import com.dvduy.service.INewsService;
import com.dvduy.sort.Sorter;
import com.dvduy.utils.FormUtil;
import constant.SystemConstant;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/admin-news-list" })

public class NewsController extends HttpServlet {


    @Inject private INewsService newsService;

    @Inject private ICategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // /admin-news-list?page=1&maxItemInPage=10&sortBy=id&sortType=asc
        NewsModel newsModel = FormUtil.toModel(NewsModel.class, req);
        String view = "";
        if (newsModel.getType().equals(SystemConstant.LIST)){
            Pageble pageble = new PageRequest(newsModel.getPage(), newsModel.getMaxItemInPage(),new Sorter(newsModel.getSortBy(),newsModel.getSortType()));
            Integer offset = (newsModel.getPage()-1) * newsModel.getMaxItemInPage();
            newsModel.setListResult(newsService.findAll(pageble));
            newsModel.setTotalItem(newsService.getTotalItem());
            newsModel.setTotalPage((int) Math.ceil( (double) newsModel.getTotalItem() / newsModel.getTotalPage()));
            view = "views/admin/news/list.jsp";

        } else if (newsModel.getType().equals(SystemConstant.EDIT)){
            if (newsModel.getId() != null) { //sửa bài viết
                newsModel = newsService.findById(newsModel.getId());
            }
            req.setAttribute("categoryList", categoryService.findAll());
            view = "views/admin/news/edit.jsp";
        }
        req.setAttribute(SystemConstant.MODEL, newsModel);
        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
        dispatcher.forward(req, resp);
    }
}
