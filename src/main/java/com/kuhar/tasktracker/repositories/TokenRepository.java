package com.kuhar.tasktracker.repositories;

import com.kuhar.tasktracker.models.Token;
import com.kuhar.tasktracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);

    boolean existsTokenByToken(String token);

    void deleteAllByUser(User user);

    List<Token> findTokensByUser(User user);
}
