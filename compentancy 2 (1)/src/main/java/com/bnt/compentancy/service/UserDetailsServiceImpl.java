/*
 * package com.bnt.compentancy.service;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.bnt.compentancy.dao.UserJpaRepository; import
 * com.bnt.compentancy.entity.User;
 * 
 * @Service public class UserDetailsServiceImpl implements UserDetailsService{
 * 
 * @Autowired private UserJpaRepository userRepository;
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException {
 * 
 * User user = this.userRepository.findByEmail(username);
 * 
 * if(user == null) { System.out.println("User Not Found"); throw new
 * UsernameNotFoundException("User Not Found"); }
 * 
 * 
 * return user; }
 * 
 * 
 * 
 * }
 */