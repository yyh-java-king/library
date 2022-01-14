package com.atguigu.utility;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2021/12/15 - 22:22
 */
public class FileUtils {

    /**
     * 客户端请求下载文件
     *
     * @param path     服务器中要下载的文件路径
     * @param fileName 客户端下载到的文件的文件名
     */
    public static void FileDownload(String path, String fileName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*获取servletContext对象*/
        ServletContext servletContext = req.getServletContext();
        /*选择要下载的文件路径*/
        //String path = "/img/wbb1.jpg";
        /*读取要下载的文件内容*/
        InputStream inputStream = servletContext.getResourceAsStream(path);
        /*获取要下载的文件类型*/
        String mimeType = servletContext.getMimeType(path);
        System.out.println("要下载的文件类型:" + mimeType);
        /*通过响应头告诉客户端回传的数据类型*/
        resp.setContentType(mimeType);
        /*客户端下载到的文件名*/
        //String fileName = "王冰冰1.jpg";
        if (req.getHeader("User-Agent").contains("Firefox")) {
            /*如果是火狐浏览器请求则使用BASE64编码*/
            fileName = "Content-Disposition: attachment; filename==?UTF-8?B?" +
                    new BASE64Encoder().encode(fileName.getBytes("UTF-8")) + "?=";
        } else {
            /*如果不是火狐则使用URL编码*/
            fileName = "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8");
        }
        /*
         * 告诉客户端收到的数据是用于下载使用
         * Content-Disposition响应头,表示收到的数据怎么处理
         * attachment : 表示附件,表示下载使用
         * filename=表示指定下载的文件名
         * */
        resp.setHeader("Content-Disposition", fileName);
        /*获取响应的输出流*/
        ServletOutputStream outputStream = resp.getOutputStream();
        /*把输入流的内容读到输出流*/
        IOUtils.copy(inputStream, outputStream);
    }


    /**
     * 客户端文件上传
     *
     * @param path 要保存到的文件夹路径 如 :
     *             "D:\\develop\\IDEACode\\JavaWeb\\EL_JSTL\\img\\"
     */
    public static void fileUpload(String path, HttpServletRequest req) throws UnsupportedEncodingException {
        /*设置上传的字符集,防止乱码*/
        req.setCharacterEncoding("UTF-8");
        /*获取ServletFileUpload类的对象*/
        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
        // 判断当前上传的数据格式是否为多段格式
        if (ServletFileUpload.isMultipartContent(req)) {
            try {
                /*获取上传到服务器的所有数据*/
                List<FileItem> list = servletFileUpload.parseRequest(req);
                for (FileItem fileItem : list) {
                    /*判断上传的数据是普通类型还是文件类型*/
                    if (!fileItem.isFormField()) {
                        /*文件类型及保存到本地保存到本地*/
                        fileItem.write(new File(path + fileItem.getName()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
