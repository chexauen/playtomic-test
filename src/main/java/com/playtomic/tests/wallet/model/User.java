package com.playtomic.tests.wallet.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends IdentifiableModel{

    @NotBlank
    private String username;

    @Email
    private String email;

    @Size(min = 8)
    private String password;
}
