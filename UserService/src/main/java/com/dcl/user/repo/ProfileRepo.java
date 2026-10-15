package com.dcl.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dcl.user.entity.Profile;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Integer>{
	Optional<Profile> findByUserUserId(Integer userId);

}
