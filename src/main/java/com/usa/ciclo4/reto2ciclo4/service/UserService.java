package com.usa.ciclo4.reto2ciclo4.service;

import com.usa.ciclo4.reto2ciclo4.model.User;
import com.usa.ciclo4.reto2ciclo4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.getAll();
    }

    public Optional<User> getUser(int id){
        return userRepository.getUser(id);
    }

    public User save(User user){
        if (user.getId() != null){
            Optional<User> revision=userRepository.getUser(user.getId());
            if (revision.isEmpty()){
                return userRepository.save(user);
            }else{
                return user;
            }
        }else {
            return user;
        }
    }

    public User update(User user) {
        if(user.getId() != null) {
            Optional<User> dbUser = userRepository.getUser(user.getId());
            if (!dbUser.isEmpty()) {
                if (user.getIdentification() != null) {
                    dbUser.get().setIdentification(user.getIdentification());
                }
                if (user.getName() != null) {
                    dbUser.get().setName(user.getName());
                }
                if (user.getBirthtDay() != null){
                    dbUser.get().setBirthtDay(user.getBirthtDay());
                }

                if (user.getMonthBirthtDay() != null){
                    dbUser.get().setMonthBirthtDay(user.getMonthBirthtDay());
                }
                if (user.getAddress() != null) {
                    dbUser.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null) {
                    dbUser.get().setCellPhone(user.getCellPhone());
                }
                if (user.getEmail() != null) {
                    dbUser.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    dbUser.get().setPassword(user.getPassword());
                }
                if (user.getZone() != null) {
                    dbUser.get().setZone(user.getZone());
                }
                userRepository.save(dbUser.get());
                return dbUser.get();
            } else {
                return user;
            }
        }else{
            return user;
        }
    }

    public boolean delete(int userId) {
        Boolean userBoolean = getUser(userId).map(user ->{
            userRepository.delete(user);
            return true;
        }).orElse(false);
        return userBoolean;
    }

    public boolean emailExists(String email) {
        return userRepository.emailExists(email);
    }

    public User authenticateUser (String email, String password) {
        Optional<User> user = userRepository.authenticateUser(email, password);
        if(user.isEmpty()){
            return new User();
        } else{
            return user.get();
        }
    }

    public List<User>getByMonthBirthDay(String monthBirthDay){
        return userRepository.getByMonthBirthtDay(monthBirthDay);
    }
}
