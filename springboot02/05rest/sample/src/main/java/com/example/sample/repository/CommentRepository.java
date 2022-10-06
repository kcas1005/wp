package com.example.sample.repository;

import com.example.sample.model.entity.CommentEntity;
import com.example.sample.model.entity.LoginEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<CommentEntity,Integer> {
    @Override
    ArrayList<CommentEntity> findAll();
    @Query(value =
            "SELECT * " +
                    "FROM comment_entity " +
                    "WHERE login_entity_id = :loginEntityId",
            nativeQuery = true)
    ArrayList<CommentEntity> findByLoginEntityId(@Param("loginEntityId") Integer loginEntityId);
}
