package ru.grabovsky.patterns.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UnitOfWork {

    private final Connection conn;

    private final List<User> newUsers = new ArrayList<>();
    private final List<User> updateUsers = new ArrayList<>();
    private final List<User> deleteUsers = new ArrayList<>();

    public UnitOfWork(final Connection conn) {
        this.conn = conn;
    }


    public void registerNew(User user) {
        this.newUsers.add(user);
    }

    public void registerUpdate(User user) {
        this.updateUsers.add(user);
    }

    public void registerDelete(User user) {
        this.deleteUsers.add(user);
    }

    public void commit() {
        try {
            insert();
            update();
            delete();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insert() throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO users (id, login, password) VALUES(?, ? ?);");
        for (User user : newUsers) {
            statement.setLong(1, user.getId());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.execute();
        }
        newUsers.clear();
    }

    private void update() throws SQLException {
        PreparedStatement statement = conn.prepareStatement("UPDATE users SET login = ?, password = ? WHERE id = ?;");
        for (User user : updateUsers) {
            statement.setLong(3, user.getId());
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.execute();
        }
        updateUsers.clear();
    }

    private void delete() throws SQLException {
        PreparedStatement statement = conn.prepareStatement("DELETE FROM users WHERE id = ?;");
        for (User user : deleteUsers) {
            statement.setLong(1, user.getId());
            statement.execute();
        }
        deleteUsers.clear();
    }

}
