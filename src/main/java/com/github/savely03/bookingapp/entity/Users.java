package com.github.savely03.bookingapp.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Users {
    @Id
    private Long id;
    private String username;
    @Column("full_name")
    private String fullName;
    private String password;
    private Role role;
    private String email;
}
