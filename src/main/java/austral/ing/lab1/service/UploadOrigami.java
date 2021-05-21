package austral.ing.lab1.service;

import austral.ing.lab1.entity.Origamis;
import austral.ing.lab1.model.Image;
import austral.ing.lab1.model.Origami;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/upload.do")
@MultipartConfig
public class UploadOrigami extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Origami origami = new Origami();

        origami.setTitle(req.getParameter("title"));
        origami.setDifficulty(req.getParameter("difficulty"));
        origami.setCategory(req.getParameter("category"));
        origami.setDetails(req.getParameter("details"));
        List<Part> fileParts = req.getParts().stream().filter(part -> "file".equals(part.getName()) && part.getSize() > 0).collect(Collectors.toList()); // Retrieves <input type="file" name="file" multiple="true">

        List<Image> images = new ArrayList<>();
        for (Part filePart : fileParts) {
            InputStream fileContent = filePart.getInputStream();
            Image image = new Image();
            byte[] byteData = IOUtils.toByteArray(fileContent);
            image.setData(byteData);
            images.add(image);
        }

        origami.setImages(images);
        Origamis.persist(origami);

        final RequestDispatcher view = req.getRequestDispatcher("/secure/home.html");
        view.forward(req, resp);
    }

}
