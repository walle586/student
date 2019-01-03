package com.example.sims.service;

import com.example.sims.domain.Administrator;
import com.example.sims.repository.AdministratorRepository;

import javax.annotation.Resource;

public class AdministratorService {

    @Resource
    private AdministratorRepository administratorRepository;

    public boolean addAdmin(Administrator administrator) {
        try {
            administratorRepository.save(administrator);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean login(String username, String password) {
        Administrator administrator = administratorRepository.findAdministratorByUsernameAndPassword(username, password);
        if (administrator == null) {
            return false;
        } else {
            return true;
        }
    }

}
