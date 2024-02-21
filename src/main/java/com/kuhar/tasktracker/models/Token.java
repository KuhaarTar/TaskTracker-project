package com.kuhar.tasktracker.models;

import com.kuhar.tasktracker.models.enums.TokenType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Table(name = "token")
@AllArgsConstructor
@NoArgsConstructor
public class Token extends BaseEntity {
    @Column(nullable = false)
    private String token;
    @Enumerated(EnumType.STRING)
    private TokenType type;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
}
