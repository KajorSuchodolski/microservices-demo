package com.example.accountservice.security.service;

import com.example.accountservice.entity.Account;
import com.example.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Stream;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public UserDetailsServiceImplementation(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Account by given login: " + username + " has not been found"));

        Collection<SimpleGrantedAuthority> authorities = Stream.of(account.getRoles().split(",")).map(SimpleGrantedAuthority::new)
                .toList();

        return new User(account.getLogin(), account.getPassword(), authorities);
    }
}
