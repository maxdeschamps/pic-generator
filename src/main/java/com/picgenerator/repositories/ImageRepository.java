package com.picgenerator.repositories;

import com.picgenerator.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    @Query("select i from Image as i join i.tags as t where t.id = ?1")
    List<Image> findByTag(Integer tag);
}
