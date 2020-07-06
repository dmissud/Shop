package org.dbs.shop.infratructure.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserJpaRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String name);
}
