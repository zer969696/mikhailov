package repository;

import database.DatabaseConnection;
import model.City;
import model.Concert;
import model.Place;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static database.DbConstants.*;

public class ConcertRepository {

    private Connection dbConnection;

    public ConcertRepository() {
        this.dbConnection = DatabaseConnection.getConnection();
    }

    public List<Concert> findAllConcerts() {
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT * FROM agent.concert");

            ResultSet rs =  preparedStatement.executeQuery();
            if (rs != null) {
                List<Concert> concerts = new ArrayList<Concert>();
                while (rs.next()) {
                    Concert concert = new Concert();

                    concert.setId(rs.getInt(CONCERT_ID));
                    concert.setDate(rs.getDate(CONCERT_DATE));
                    concert.setPrice(rs.getLong(CONCERT_PRICE));

                    CityRepository cityRepository = new CityRepository();
                    City city = cityRepository.findById(rs.getInt(CONCERT_ID_CITY));

                    PlaceRepository placeRepository = new PlaceRepository();
                    Place place = placeRepository.findById(rs.getInt(CONCERT_ID_PLACE));

                    concert.setPlace(place);
                    concert.setCity(city);

                    concerts.add(concert);
                }

                return concerts;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
