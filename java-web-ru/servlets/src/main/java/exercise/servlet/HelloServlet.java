package exercise.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("name");

        String message = (param == null || param.isEmpty()) ? "Guest" : param;
        req.setAttribute("message", String.format("Hello, %s!", message));

        req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req, resp);
    }
}
