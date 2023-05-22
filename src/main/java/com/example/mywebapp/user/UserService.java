package com.example.mywebapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository repo;
    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer id) throws UsernotFoundException {
        Optional<User> result =repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UsernotFoundException("could not find user wwith that id"+id);
    }
    public  void deletebyid(Integer id) throws UsernotFoundException {
        Long count =repo.countById(id);
        if(count ==null|| count==0)
        {throw  new UsernotFoundException("could not find user with id"+id);}
        repo.deleteById(id);
    }
}
