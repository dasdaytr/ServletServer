package test.services;

import test.DAO.DataBase;
import test.Model.Files;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class DownloadsService  {
    private String PATH = "D:\\JavaProject\\ServletProject\\demo1\\src\\main\\webapp\\files\\";

    public void download(HttpServletRequest request, HttpServletResponse response, String relativePath,ServletContext context) throws IOException {
        String filePath = PATH + request.getParameter("filename");
        File downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);

        System.out.println("relativePath = " + relativePath);

        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";//двоичный файл без указания формата, чтобы загрузить любой файл
        }
        System.out.println("MIME type: " + mimeType);

        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inStream.close();
        outStream.close();
    }
}
