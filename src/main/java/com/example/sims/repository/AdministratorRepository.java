package com.example.sims.repository;

import com.example.sims.domain.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, String> {
    Administrator findAdministratorByUsername(String username);

    Administrator findAdministratorByUsernameAndPassword(String username, String password);
}
