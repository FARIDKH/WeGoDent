package com.company.WeGoDent.security.services;



import com.company.WeGoDent.dto.UserDTO;
import com.company.WeGoDent.entity.GroupRole;
import com.company.WeGoDent.exceptions.DuplicateException.DuplicateException;
import com.company.WeGoDent.entity.User;
import com.company.WeGoDent.mapper.UserMapper;
import com.company.WeGoDent.repositories.GroupRoleRepository;
import com.company.WeGoDent.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "userService")
@RequiredArgsConstructor
@Transactional
public class UserDetailsServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRoleRepository groupRoleRepository;

    @Autowired
    private UserMapper userMapper;


    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    /**
     * Load user info by credential
     *
     * @param usernameValue username or email
     * @return UserDetails object
     */
    @Override
    public UserDetails loadUserByUsername(String usernameValue) {
        Optional<User> user = getUserByUsername(usernameValue);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        detailsChecker.check(user.get());
        return user.get();
    }


    /**
     * @param usernameValue username or email
     * @return Optional User
     */
    @Override
    public Optional<User> getUserByUsername(String usernameValue) {
        // trim username value
        var username = StringUtils.trimToNull(usernameValue);
        if (StringUtils.isEmpty(username)) {
            return Optional.empty();
        }
        return username.contains("@") ? userRepository.findActiveByEmail(username) : userRepository.findActiveByUsername(username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User save(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new DuplicateException("Username Already exist !!");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateException("Email Already exist !!");
        }

        return userRepository.save(user);
    }

    @Override
    public User createUserFromDTO(UserDTO dto) {
        User user = userMapper.toEntity(dto);
        List<GroupRole> roles = dto.getRoleIds().stream()
                .map(groupRoleRepository::findById) // Assuming that RoleRepository::findById returns Optional<GroupRole>
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        user.setRoles(roles);
        return user;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

}

