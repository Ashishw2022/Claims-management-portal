package com.mfpe.surveyor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfpe.surveyor.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);

}