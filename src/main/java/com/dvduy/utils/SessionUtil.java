package com.dvduy.utils;


import javax.servlet.http.HttpServletRequest;

public class SessionUtil {


    //giảm lãng phí bộ nhớ
    private static SessionUtil sessionUtil=null;
    //kiểm tra session có tồn tại hay không nếu không tồn tại thì tạo mới
    public static SessionUtil getInstance() {
        if (sessionUtil == null) {
            sessionUtil = new SessionUtil();
        }
        return sessionUtil;
    }




    // gán giá trị vào session
    public void putValue(HttpServletRequest request, String key, Object value) { //session là 1 đối tượng có thể được lưu trữ trong request
        request.getSession().setAttribute(key,value);
    }

    // lấy giá trị từ session
    public Object getValue(HttpServletRequest request,String key) { //do chưa biết trả về giá trị nào nên trả về object sau đó ép kiểu object sang kiểu giá trị cần dùng
        return request.getSession().getAttribute(key);
    }

    // xóa giá trị từ session
    public void removeValue(HttpServletRequest request,String key) {
        request.getSession().removeAttribute(key);
    }
}
