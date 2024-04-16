package fr.norsys.ApiDoc.service;

import fr.norsys.ApiDoc.model.User;
import fr.norsys.ApiDoc.repository.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest extends BaseTest {
    @Autowired
    UserService userService;
    @Test
    void should_return_all_users(){
        List<User> users=userService.findAllUsers();
        assertEquals(1,users.size());
    }
    @Test
    @Rollback
    public void should_save_user() {
        User user = new User(0L,"jdoe","password","jdoe@gmail.com");
        long userId = userService.saveUser(user).getIdUser();
        assertTrue(userId > 0);
    }
    @Test
    public void should_find_user_by_id() {
        User user = userService.findUserById(1L);
        assertEquals("test", user.getUsername());
    }

}
