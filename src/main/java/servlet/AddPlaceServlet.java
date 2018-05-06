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

@WebServlet("/add-place")
public class AddPlaceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<City> allCities = new CityRepository().findAllCities();

        req.setAttribute("cities", allCities);

        getServletContext().getRequestDispatcher("/add-place.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String capacity = req.getParameter("capacity");
        String idCity = req.getParameter("city");

        Place place = new Place();

        place.setId(0);
        place.setCapacity(Long.valueOf(capacity));
        place.setName(name);
        place.setCity(new CityRepository().findById(Integer.valueOf(idCity)));

        new PlaceRepository().save(place);

        resp.sendRedirect("/main.jsp");
    }
}
