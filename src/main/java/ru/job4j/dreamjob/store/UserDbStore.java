package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class UserDbStore {
    private static final Logger LOG = LogManager.getLogger(CandidateDbStore.class.getName());

    private final BasicDataSource pool;

    public UserDbStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Optional<User> add(User user) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("insert into users (name) values (?);",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return user.getId() == 0 ? Optional.empty() : Optional.of(user);
    }

    public User findUserByName(String name) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("select id, name from users where name like ?")) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("name"));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }
}
