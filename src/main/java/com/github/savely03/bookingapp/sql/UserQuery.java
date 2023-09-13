package com.github.savely03.bookingapp.sql;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserQuery {

    public static final String INSERT = """
            INSERT INTO users (username, full_name, password, role, email)
            VALUES (:username, :full_name, :password, :role, :email)
            RETURNING id
            """;

    public static final String FIND_ALL = """
            SELECT id,
                   username,
                   full_name,
                   password,
                   role,
                   email
            FROM users
            """;

    public static final String FIND_BY_ID = FIND_ALL + " WHERE id = :id";

    public static final String EXISTS = """
            SELECT 1
            FROM users
            WHERE id = :id
            """;
}
