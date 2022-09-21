package com.garciajuanjo.RegistroUsuariosSpring.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.garciajuanjo.RegistroUsuariosSpring.constant.MessagesConstant.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserApp {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "birthDate")
    private String birthDate;

    @Column(name = "dischargeDate", nullable = false)
    private Date dischargeDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private Set<Roles> roles = new HashSet<>();

    public UserApp(@NotEmpty(message = MESSAGE_USSERNAME_REQUIRED)
                   @NotBlank(message = MESSAGE_USSERNAME_NOT_BLANK) String username,
                   @NotBlank(message = MESSAGE_EMAIL_REQUIRED) @Email String email,
                   @NotBlank(message = MESSAGE_PHONE_REQUIRED) String phone,
                   @Past @NotNull(message = MESSAGE_BIRTH_DATE_REQUIRED) String birthDate,
                   Date dischargeDate) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.dischargeDate = dischargeDate;
    }

    @PrePersist
    private void prepersist() {
        this.dischargeDate = new Date();
    }

}
