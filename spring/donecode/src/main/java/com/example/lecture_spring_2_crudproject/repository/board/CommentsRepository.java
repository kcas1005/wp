package com.example.lecture_spring_2_crudproject.repository.board;

import com.example.lecture_spring_2_crudproject.entity.board.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    @Query(value = "SELECT c FROM Comments c JOIN fetch c.board Where c.board.seq = :input_board_seq")
    List<Comments> findCommentsByBoard_seq(Long input_board_seq);
}
