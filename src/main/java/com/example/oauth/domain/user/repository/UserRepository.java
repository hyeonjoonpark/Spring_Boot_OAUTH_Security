package com.example.oauth.domain.user.repository;

import com.example.oauth.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByUserName(String userName);
}
