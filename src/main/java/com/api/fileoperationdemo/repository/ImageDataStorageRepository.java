package com.api.fileoperationdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.fileoperationdemo.entity.ImageData;

public interface ImageDataStorageRepository extends JpaRepository<ImageData, Long> {

    Optional<ImageData> findByName(String flieName);

}
