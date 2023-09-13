package com.github.savely03.bookingapp.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Users {
    private Long id;
    private String username;
    private String fullName;
    private String password;
    private Role role;
    private String email;
}
