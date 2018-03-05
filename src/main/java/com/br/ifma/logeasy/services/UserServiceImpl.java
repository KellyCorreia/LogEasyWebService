package com.br.ifma.logeasy.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.ifma.logeasy.domain.User;
import com.br.ifma.logeasy.repositories.UserRepository;

@Service
@Profile("springdatajpa")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); //fun with Java 8
        return users;
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findOne(id);
    }
    
    @Override
      @Transactional
       public void delete(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	@Override
	public User saveOrUpdate(User domainObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User criptografaSenha(User usuario) {
		// TODO Auto-generated method stub
		return null;
	}
}
