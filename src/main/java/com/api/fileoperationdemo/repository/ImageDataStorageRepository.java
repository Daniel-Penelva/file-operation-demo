package com.api.fileoperationdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.fileoperationdemo.entity.ImageData;

public interface ImageDataStorageRepository extends JpaRepository<ImageData, Long>{
    
}
