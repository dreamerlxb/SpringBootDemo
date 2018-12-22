package com.lxb.demo.security;

import java.util.List;

import com.lxb.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lxb.demo.role.Role;
import com.lxb.demo.role.RoleRepository;
import com.lxb.demo.user.User;

@Service("jwtUserDetailsService")
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            List<Role> roles = roleRepository.findRolesByUserId(user.getId());

            user.setRoles(roles);

            return new JwtUser(user.getId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getRoles(),
                    user.getLastPasswordResetDate()
            );
        }
    }
}
