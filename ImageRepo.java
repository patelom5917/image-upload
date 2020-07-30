package com.om.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.om.model.ImageModel;

public interface ImageRepo extends JpaRepository<ImageModel, Long>
{

	Optional<ImageModel> findByName(String imageName);
	
}
