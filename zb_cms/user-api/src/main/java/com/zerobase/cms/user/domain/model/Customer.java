package com.zerobase.cms.user.domain.model;

import com.zerobase.cms.user.domain.SignUpForm;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    private String phone;
    private LocalDate birth;
    private Integer Balance;

    private LocalDateTime verifyExpiredAt;
    private String verificationCode;
    private boolean verify;

    public static Customer from(SignUpForm form) {
        return Customer.builder()
            .email(form.getEmail().toLowerCase(Locale.ROOT))
            .password(form.getPassword())
            .name(form.getName())
            .birth(form.getBirth())
            .phone(form.getPhone())
            .verify(false)
            .build();
    }
}
