package springsecurity.dao.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsecurity.dao.entities.Authority;
import springsecurity.dao.entities.Role;
import springsecurity.dao.entities.User;
import springsecurity.dao.repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Stream.concat(
                        mapRolesToAuthorities(user.getRoles()).stream(),
                        mapAuthoritiesToAuthorities(user.getRoles(), user.getAuthorities()).stream()
                ).collect(Collectors.toList()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    private Collection<? extends GrantedAuthority> mapAuthoritiesToAuthorities(Collection<Role> roles, Collection<Authority> authorities) {
        Set<Authority> allRolesAuthorities = new HashSet<>();

        for (Role role: roles){
            allRolesAuthorities.addAll(role.getAuthorities());
        }

        Collection<? extends GrantedAuthority> userRolesAuthorities =
                allRolesAuthorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).distinct().collect(Collectors.toList());
        Collection<? extends GrantedAuthority> userManualAuthorities =
                authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).distinct().collect(Collectors.toList());

        return Stream.concat(
                userRolesAuthorities.stream(),
                userManualAuthorities.stream()
        ).collect(Collectors.toList());
    }
}