package org.dbs.shop.infratructure.user;

import org.dbs.shop.domain.user.User;
import org.dbs.shop.infratructure.common.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper extends AbstractMapper<User, UserEntity> {
    @Override
    public User mapToDomain(UserEntity userEntity) {
        User user = new User(userEntity.getUserName(),
                userEntity.getEmail(),
                userEntity.getRoles());
        user.changePassword(userEntity.getPassword());
        return user;
    }

    @Override
    public UserEntity mapToEntity(User user) {
        final UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getName());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userEntity.setRoles(user.getRoles());
        return userEntity;
    }
}
