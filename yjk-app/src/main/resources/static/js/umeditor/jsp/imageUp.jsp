    <%@ page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"%>
    <%@ page import="com.snncar.util.Uploader" %>
    <%@ page import="com.snncar.common.FileConstants" %>

    <%
    request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
    Uploader up = new Uploader(request);
    String[] fileType = {".gif", ".jpg", ".jpeg", ".png" , ".bmp", ".ico"};
    up.setAllowFiles(fileType);
    up.setMaxSize(Integer.parseInt(FileConstants.IMAGE_MAX_SIZE)); //单位KB
    up.upload();

    String type = request.getParameter("type");
    String editorId = request.getParameter("editorid");

    if( type != null &&  "ajax".equals( type ) ){
        response.getWriter().print( up.getUrl() );
    }else{
        response.getWriter().print("<script>parent.UM.getEditor('"+ editorId +"').getWidgetCallback('image')('" + up.getUrl() + "','" + up.getState() + "')</script>");
    }
    %>
