package com.fnb.orderservice.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPrincipal {
    private final Long customerId;

    private final String username;

    private final String role;
}
