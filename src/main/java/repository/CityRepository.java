package repository;

import database.DatabaseConnection;
import database.DbConstants;
import model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static database.DbConstants.*;

public class CityRepository {

    private Connection dbConnection;

    public CityRepository() {
        this.dbConnection = DatabaseConnection.getConnection();
    }

    public City findById(int id) {
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT * FROM agent.city WHERE id = ?");
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs != null) {
                rs.next();

                City city = new City();

                city.setId(rs.getInt(CITY_ID));
                city.setName(rs.getString(CITY_NAME));

                return city;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public List<City> findAllCities() {
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT * FROM agent.city");

            ResultSet rs = preparedStatement.executeQuery();
            if (rs != null) {
                List<City> cities = new ArrayList<City>();

                while(rs.next()) {
                    City city = new City();

                    city.setId(rs.getInt(CITY_ID));
                    city.setName(rs.getString(CITY_NAME));

                    cities.add(city);
                }

                return cities;
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
