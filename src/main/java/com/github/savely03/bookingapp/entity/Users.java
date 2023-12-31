package com.github.savely03.bookingapp.entity;

import com.github.savely03.bookingapp.security.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users implements BaseEntity<Long> {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;
}
