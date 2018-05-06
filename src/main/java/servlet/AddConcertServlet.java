package servlet;

import model.City;
import model.Place;
import repository.CityRepository;
import repository.PlaceRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/add-concert")
public class AddConcertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<City> cities = new CityRepository().findAllCities();
        //List<Place> places = new PlaceRepository().findAllPlaces();

        req.setAttribute("cities", cities);
        //req.setAttribute("places", places);

        getServletContext().getRequestDispatcher("/add-concert.jsp").forward(req, resp);
    }
}
