package ru.saubulprojects.tinkoffapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ru.saubulprojects.tinkoffapp.model.User;

public interface UserService extends UserDetailsService{

	User findByUsername(String username);

	User save(User user);

}
