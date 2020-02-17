package com.geekbrains.chat.server;

import java.sql.*;

public class BasicAuthManager implements AuthManager {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:NetworkChatDB.db");
        stmt = connection.createStatement();
    }

    public static void disconnect() {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //public static List<String> getClientsList() throws SQLException, ClassNotFoundException {
    //    String sql = String.format("Select nickname FROM Clients;");
    //    List<String> clients = new ArrayList<>();
    //    ResultSet resultSet = stmt.executeQuery(sql);
    //    if (resultSet.next()) {
    //        clients.add(resultSet.getString(1));
    //    }
    //    return clients;
    //}

    public static void setNewNickname(String nickname, String newNickName) throws SQLException {
                stmt.executeUpdate("UPDATE Clients SET nickname = '" + newNickName + "' WHERE nickname = '" + nickname + "';");
    }


    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        String sql = String.format("SELECT nickname FROM Clients WHERE login = '%s'", login, password);

        try {
            ResultSet resultSet = stmt.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
