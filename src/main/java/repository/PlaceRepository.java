package repository;

import database.DatabaseConnection;
import model.City;
import model.Place;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static database.DbConstants.*;

public class PlaceRepository {

    private Connection dbConnection;

    public PlaceRepository() {
        this.dbConnection = DatabaseConnection.getConnection();
    }

    public Place findById(int id) {
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT * FROM agent.place WHERE id = ?");
            preparedStatement.setInt(1, id);

            ResultSet rs =  preparedStatement.executeQuery();
            if (rs != null) {
                rs.next();

                Place place = new Place();

                place.setId(rs.getInt(PLACE_ID));
                place.setName(rs.getString(PLACE_NAME));
                place.setCapacity(rs.getLong(PLACE_CAPACITY));

                CityRepository cityRepository = new CityRepository();
                City city = cityRepository.findById(rs.getInt(PLACE_ID_CITY));
                place.setCity(city);

                return place;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public List<Place> findAllPlacesByCityId(int id) {
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT * FROM agent.place WHERE id_city = ?");
            preparedStatement.setInt(1, id);

            ResultSet rs =  preparedStatement.executeQuery();
            if (rs != null) {
                List<Place> places = new ArrayList<Place>();

                while(rs.next()) {

                    Place place = new Place();

                    place.setId(rs.getInt(PLACE_ID));
                    place.setName(rs.getString(PLACE_NAME));
                    place.setCapacity(rs.getLong(PLACE_CAPACITY));

                    CityRepository cityRepository = new CityRepository();
                    City city = cityRepository.findById(rs.getInt(PLACE_ID_CITY));
                    place.setCity(city);

                    places.add(place);
                }

                return places;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public List<Place> findAllPlaces() {
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT * FROM agent.place");

            ResultSet rs = preparedStatement.executeQuery();
            if (rs != null) {
                List<Place> places = new ArrayList<Place>();

                while(rs.next()) {
                    Place place = new Place();

                    place.setId(rs.getInt(PLACE_ID));
                    place.setName(rs.getString(PLACE_NAME));
                    place.setCapacity(rs.getInt(PLACE_CAPACITY));
                    place.setCity(new CityRepository().findById(rs.getInt(PLACE_ID_CITY)));

                    places.add(place);
                }

                return places;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public void save(Place place) {
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement("INSERT INTO agent.place (name, capacity, id_city) VALUES (?, ?, ?)");
            preparedStatement.setString(1, place.getName());
            preparedStatement.setLong(2, place.getCapacity());
            preparedStatement.setInt(3, place.getCity().getId());

            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
