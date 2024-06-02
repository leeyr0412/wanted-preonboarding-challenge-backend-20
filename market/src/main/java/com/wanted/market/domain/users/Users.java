package com.wanted.market.domain.users;

import com.wanted.market.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`users`")
@Getter
@NoArgsConstructor
public class Users extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String loginId;

    @Column(nullable = false, length = 60)
    private String encryptedPw;

    @Builder
    private Users(String loginId, String encryptedPw) {
        this.loginId = loginId;
        this.encryptedPw = encryptedPw;
    }
}
