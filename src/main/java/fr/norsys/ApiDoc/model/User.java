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
    private long idUser;
    private String username;
    private String password;
    private String email;
    public User(String username,String password,String mail){
        this.username=username;
        this.password=password;
        this.email=mail;
    }
    public static User baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setIdUser(resultSet.getLong("idUser"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword((resultSet.getString("password")));
        user.setUsername(resultSet.getString("username"));

        return user;
    }
}
