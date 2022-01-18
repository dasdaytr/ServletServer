package test.services;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import test.DAO.DataBase;
import test.Model.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class UploadFilesService {
    private  static String UPLOAD_DIRECTORY = "D:\\JavaProject\\ServletProject\\demo1\\src\\main\\webapp\\files\\";

    public void uploadFiles(HttpServletRequest req) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(req)) {

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            req.setAttribute("path",UPLOAD_DIRECTORY);
            File uploadDir = new File(UPLOAD_DIRECTORY);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            List<FileItem> formItems = null;
            try {
                formItems = upload.parseRequest(req);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            addFilesToServer(formItems);
        }
    }

    private static void addFilesToServer( List<FileItem> formItems){
        if (formItems != null && formItems.size() > 0) {
            for (FileItem item : formItems) {
                if (!item.isFormField()) {
                    String [] infoFile = new File(item.getName()).getName().split("\\.");// получаем название и расширение файла
                    String fileNameUnique = String.valueOf(ThreadLocalRandom.current().nextInt()) + infoFile[0];
                    new DataBase().addNewFile(new Files(infoFile[0],fileNameUnique,infoFile[1]));
                    String filePath = UPLOAD_DIRECTORY + fileNameUnique;
                    File storeFile = new File(filePath);
                    try {
                        item.write(storeFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
