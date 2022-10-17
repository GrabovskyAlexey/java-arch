package ru.grabovsky.patterns.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserMapper {

    private final Connection conn;

    private final PreparedStatement statement;

    private final Map<Long, User> identityMap = new HashMap<>();

    public UserMapper(final Connection conn) {
        this.conn = conn;
        try {
            statement = conn.prepareStatement("SELECT * FROM users WHERE id = ?;");
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Optional<User> findById(Long id){
        final User user = identityMap.get(id);
        if(user != null){
            return Optional.of(user);
        }
        try {
            statement.setLong(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                final User newUser = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                identityMap.put(id, newUser);
                return Optional.of(newUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
