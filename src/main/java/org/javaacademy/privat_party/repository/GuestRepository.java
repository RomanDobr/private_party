package org.javaacademy.privat_party.repository;

import org.javaacademy.privat_party.entity.GuestList;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GuestRepository {
  private static final String URL_BASE = "jdbc:postgresql://192.168.2.10:5432/private_party";
  private static final String DRIVER = "org.postgresql.Driver";
  private static final String SQL_INSERT = "insert into guest (name, email, phone) values ('%s', '%s', '%s');";
  private static final String SQL_SELECT_NAME = "select * from guest_name;";

  public void addGuest(String name, String email, String phone, String login, String password)
          throws SQLException, ClassNotFoundException {
    Class.forName(DRIVER);
    try (Connection connection = DriverManager.getConnection(URL_BASE, login, password)) {
          connection.createStatement().execute(SQL_INSERT.formatted(name, email, phone));
    }
  }

  public GuestList findAllGuest(String login, String password)
          throws ClassNotFoundException, SQLException {
      Class.forName(DRIVER);
      try (Connection connection = DriverManager.getConnection(URL_BASE, login, password)) {
          ResultSet resultSet = connection.createStatement().executeQuery(SQL_SELECT_NAME);
          List<String> names = new ArrayList<>();
          while (resultSet.next()) {
            names.add(resultSet.getString("name"));
          }
          return new GuestList(names);
      }
  }
}
