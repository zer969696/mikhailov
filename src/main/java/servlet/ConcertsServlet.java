package servlet;

import model.Concert;
import repository.ConcertRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/concerts")
public class ConcertsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConcertRepository concertRepository = new ConcertRepository();
        List<Concert> concerts = concertRepository.findAllConcerts();
        req.setAttribute("concerts", concerts);
        getServletContext().getRequestDispatcher("/concerts.jsp").forward(req, resp);
    }
}
