package com.maveric.accountservice.feignclient;

import com.maveric.accountservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(value = "user", url = "http://localhost:3005/api/v1")
public interface UserServiceConsumer {

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String id,
                                               @RequestHeader(value = "userid") String headerUserId);


}
