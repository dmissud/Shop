package org.dbs.shop.infratructure.user;

import org.dbs.shop.domain.common.AllReadyExistException;
import org.dbs.shop.domain.common.NotFoundException;
import org.dbs.shop.domain.user.IRepositoryUser;
import org.dbs.shop.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositoryUserImpl implements IRepositoryUser {

    @Autowired
    IUserJpaRepository customerJpaRepository;

    @Autowired
    UserEntityMapper userEntityMapper;

    @Override
    public void save(User user) {

        UserEntity userEntity = customerJpaRepository.findByUserName(user.getName());
        if (userEntity == null) {
            customerJpaRepository.save(userEntityMapper.mapToEntity(user));
        } else {
            throw new AllReadyExistException("User All Ready Exist : " + user.getName());
        }

    }

    @Override
    public User findByName(String userName) {
        UserEntity userEntity = customerJpaRepository.findByUserName(userName);
        if (userEntity != null) {
            return userEntityMapper.mapToDomain(userEntity);
        }
        throw new NotFoundException("User Not Found : " + userName);
    }

    @Override
    public List<User> retrieveAllUsers() {
        List<UserEntity> allUserEntity = customerJpaRepository.findAll();
        return userEntityMapper.mapToDomainList(allUserEntity);
    }
}
