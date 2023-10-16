package com.finder.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finder.oauth.domain.AuthClientDetails;

@Repository
public interface AuthClientDetailRepository extends JpaRepository<AuthClientDetails, Long> {

}
