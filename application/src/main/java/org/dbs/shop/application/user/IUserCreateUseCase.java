package org.dbs.shop.application.user;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.dbs.shop.application.common.SelfValidating;
import org.dbs.shop.application.order.IOrderCreateUseCase;
import org.dbs.shop.domain.user.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


public interface IUserCreateUseCase {
    User referenceUser(CreateUserCommand createUserCmd);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class CreateUserCommand extends SelfValidating<IOrderCreateUseCase.PlaceItemCommand> {
        @Size(min = 4, max = 64)
        String userName;

        @Email
        String email;

        @NotEmpty(message = "Input roles list cannot be empty.")
        @Size(min = 1)
        List<String> roles;

        public CreateUserCommand(String userName, String email, List<String> roles) {
            this.userName = userName;
            this.email = email;
            this.roles = roles;
            this.validateSelf();
        }
    }
}
