package com.spectro.konf.dao;

import com.spectro.konf.models.Details;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DetailsDAO {

    private static int COUNTER;

    private static final String URL = "jdbc:postgresql://localhost:5432/details_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "ROOT";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<Details> index(){
        List<Details> zapchasty = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM details";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                Details details = new Details();

                details.setId(resultSet.getInt("id"));
                details.setName(resultSet.getString("name"));
                details.setType(resultSet.getString("type"));
                details.setDate(resultSet.getString("vypusk"));
                details.setPrice(resultSet.getInt("price"));
                details.setQuantity(resultSet.getInt("quantity"));
                zapchasty.add(details);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return zapchasty;

    }


    public Details show(int id){
        Details her = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM details WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            her = new Details();

            her.setId(resultSet.getInt("id"));
            her.setName(resultSet.getString("name"));
            her.setType(resultSet.getString("type"));
            her.setDate(resultSet.getString("vypusk"));
            her.setPrice(resultSet.getInt("price"));
            her.setQuantity(resultSet.getInt("quantity"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return her;

    }


    public void save(Details details){
//        details.setId(++COUNTER);
//        zapchasti.add(details);
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO details VALUES(1, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, details.getName());
            preparedStatement.setString(2, details.getType());
            preparedStatement.setString(3, details.getDate());
            preparedStatement.setInt(4, details.getPrice());
            preparedStatement.setInt(5, details.getQuantity());


            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    public void update(int id, Details updatedDetails) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE details SET name=?, type=?, vypusk=?, price=?, quantity=? WHERE id=?");

            preparedStatement.setString(1, updatedDetails.getName());
            preparedStatement.setString(2, updatedDetails.getType());
            preparedStatement.setString(3, updatedDetails.getDate());
            preparedStatement.setInt(4, updatedDetails.getPrice());
            preparedStatement.setInt(5, updatedDetails.getQuantity());
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void delete(int id){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM details WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }




}
