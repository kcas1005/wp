package com.example.sample.service;

import com.example.sample.model.entity.LoginEntity;
import com.example.sample.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public LoginEntity save(LoginEntity dto){
        LoginEntity save = loginRepository.save(dto);
        return save;
    }

    public LoginEntity findById(Integer id){
        return loginRepository.findById(id).orElse(null);
    }

    public ArrayList<LoginEntity> findAll(){
     return loginRepository.findAll();
    }

    public void delete(LoginEntity dto) {
        loginRepository.delete(dto);
    }
}
