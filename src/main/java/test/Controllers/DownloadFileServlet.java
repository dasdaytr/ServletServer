package test.Controllers;

import test.services.DownloadsService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/download")
public class DownloadFileServlet extends HttpServlet {
    private String PATH = "D:\\JavaProject\\ServletProject\\demo1\\src\\main\\webapp\\files\\";
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String relativePath = getServletContext().getRealPath("");
        ServletContext context = getServletContext();

        new DownloadsService().download(request,response,relativePath,context);
    }
}
