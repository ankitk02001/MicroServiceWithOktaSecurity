package com.ankit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankit.entity.User;

public interface UserRepository extends JpaRepository<User,String>
{
    //if you want to implement any custom method or query
    //write
}