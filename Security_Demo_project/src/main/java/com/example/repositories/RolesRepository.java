package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Roles;

public interface RolesRepository extends JpaRepository<Roles,Long> {

}