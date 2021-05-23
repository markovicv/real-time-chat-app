package com.example.ChatRealTimeService.repo;

import com.example.ChatRealTimeService.model.domain.Chat;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {

    boolean existsById(@NotNull Long id);
}
