package org.dbs.shop.application.user;

import org.dbs.shop.domain.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserManagementImpl implements IUserCreateUseCase {

    @Override
    public User referenceUser(CreateUserCommand createUserCmd) {
        return null;
    }
}
