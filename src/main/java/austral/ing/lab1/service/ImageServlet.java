package austral.ing.lab1.service;

import austral.ing.lab1.model.Image;
import austral.ing.lab1.model.Origami;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        String imageId = request.getPathInfo().substring(1);
        final Optional<Image> image = austral.ing.lab1.entity.Images.findById(Integer.valueOf(imageId).longValue());



        try {
            byte[] data = image.get().getData();
            response.setContentType(getServletContext().getMimeType(imageId));
            response.setContentLength(data.length);
            response.getOutputStream().write(data);

        } catch (Exception e) {
            e.getMessage();
        }

    }
}
