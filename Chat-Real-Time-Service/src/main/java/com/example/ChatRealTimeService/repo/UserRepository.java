package com.example.ChatRealTimeService.repo;

import com.example.ChatRealTimeService.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
