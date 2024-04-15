package fr.norsys.ApiDoc.model;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private long id;
    private String username;
    private String password;
    private String email;
    public static User baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("idUser"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword((resultSet.getString("password")));
        user.setUsername(resultSet.getString("username"));

        return user;
    }
}
