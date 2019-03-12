package com.project.base.services;

import com.project.base.objects.UserDto;
import com.project.base.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    @Override
    public User registerNewUserAccount(UserDto accountDto)
        throws EmailExistsException{
        if (emailExist(accountDto.getEmail())){
            throw new EmailExistsException(
                    "There is an account with that email address: " +accountDto.getEmail();
            )
        }

    }
    private boolean emailExists(String email){
        User user = repository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

}
