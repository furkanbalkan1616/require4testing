package com.require4testing.repository;

import com.require4testing.entity.Tester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TesterRepository extends JpaRepository<Tester, Long> {
}