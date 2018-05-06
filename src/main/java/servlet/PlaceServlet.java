package servlet;

import model.Place;
import org.json.simple.JSONObject;
import repository.PlaceRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/places")
public class PlaceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String  id = req.getParameter("idCity");
        List<Place> placeList;

        if (id == null) {
            placeList = new PlaceRepository().findAllPlaces();
        } else {
            placeList = new PlaceRepository().findAllPlacesByCityId(Integer.valueOf(id));
        }

        JSONObject jsonObject = new JSONObject();

        for (Place place : placeList) {
            jsonObject.put(place.getId(), place.getName());
        }

        resp.setContentType("application/json");
        resp.getWriter().write(jsonObject.toJSONString());
    }
}
