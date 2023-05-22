package com.example.mywebapp;
import com.example.mywebapp.user.User;
import  com.example.mywebapp.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;
    @Test
    public void testAddNew(){
        User user= new User();
        user.setEmail("user@gmail.com");
        user.setFirstName("user");
        user.setLastName("user");
        user.setPassword("123456");
        User saveduser = repo.save(user);
        Assertions.assertThat(saveduser).isNotNull();
        Assertions.assertThat(saveduser.getId()).isGreaterThan(0);
    }
    @Test
    public void testListAll(){
        Iterable<User> users=repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        for (User user: users){
            System.out.println(user);
        }
    }
    @Test
    public void testUpdate(){
        Integer userid=1;
        Optional<User> optionalUser = repo.findById(userid);
        User user = optionalUser.get();
        user.setPassword("98765");
        repo.save(user);
        User updatedUser = repo.findById(userid).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("98765");
    }
    @Test
    public void testGet(){
        Integer userid=2;
        Optional<User> optionalUser = repo.findById(userid);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }
    @Test
    public  void testDelete(){
        Integer userid=2;
        repo.deleteById(userid);
        Optional<User> optionalUser =repo.findById(userid);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
