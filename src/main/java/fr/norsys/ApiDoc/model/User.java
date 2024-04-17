package fr.norsys.ApiDoc.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
