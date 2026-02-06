package com.localexplorer.api.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Document("users")
public class User {
    @Id
    private String id;

    private String name;
    private String email;
    private String passwordHash;

    private Set<Role> roles;
}

