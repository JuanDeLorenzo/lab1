package austral.ing.lab1.service;


import austral.ing.lab1.model.Origami;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/secure/origami")
public class OrigamiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final List<Origami> origamis = austral.ing.lab1.entity.Origamis.search(req.getParameter("title")+"%", req.getParameter("category"), req.getParameter("difficulty"));

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        final Gson gson = new Gson();
        String json = gson.toJson(origamis);
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }

}
