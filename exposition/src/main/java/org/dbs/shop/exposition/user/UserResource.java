package org.dbs.shop.exposition.user;

import lombok.extern.slf4j.Slf4j;
import org.dbs.shop.application.user.IUserCreateUseCase;
import org.dbs.shop.application.user.IUserCreateUseCase.CreateUserCommand;
import org.dbs.shop.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserResource {
    @Autowired
    IUserCreateUseCase userCreateUseCase;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<URI> createCustomer(@NotNull @RequestBody CreateUserCommand createUserCmd) {
        final User user = userCreateUseCase.referenceUser(createUserCmd);
        log.debug("Users Create");
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getName()).toUri();
        return ResponseEntity.created(location).build();
    }


}
