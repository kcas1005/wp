package com.example.lecture_spring_2_crudproject.repository.board;

import com.example.lecture_spring_2_crudproject.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface BoardRepository extends JpaRepository<Board, Long> {
//    @Query(value = "select c from Comment c where c.email = :email_1 or m.id = :id_1")
//    Member findMemberByEmailOrId(String email_1, String id_1);
}
