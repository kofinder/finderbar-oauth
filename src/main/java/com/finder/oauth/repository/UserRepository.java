package com.finder.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finder.oauth.domain.User;

/**
 * @author thein
 * @createdAt Mar 16, 2019
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	@Query("SELECT DISTINCT u FROM User u WHERE u.userName = :username")
	User findByUsername(@Param("username") String username);
}
