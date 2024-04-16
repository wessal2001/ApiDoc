package fr.norsys.ApiDoc.repository;

import fr.norsys.ApiDoc.model.User;
import fr.norsys.ApiDoc.repository.impl.UserDaoImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class UserDaoTest extends BaseTest{
    @Autowired
  UserDaoImpl userDao;

    @Test
    @Rollback
    public void testSaveUser() {
        User user = new User(0L,"jdoe","password","jdoe@gmail.com");
        System.out.println(user.getIdUser());
        long userId = userDao.save(user);
        assertTrue(userId > 0);
    }

    @Test
    public void testFindById() {
        Optional<User> optionalUser = userDao.findById(1L);
        assertTrue(optionalUser.isPresent());
        assertEquals("test", optionalUser.get().getUsername());
    }
    @Test
    public void testFindAll() {
        List<User> userList = userDao.findAll();
        assertEquals(1, userList.size());
    }

}
