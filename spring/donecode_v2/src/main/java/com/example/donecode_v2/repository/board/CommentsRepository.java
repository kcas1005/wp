package com.example.donecode_v2.repository.board;

import com.example.donecode_v2.entity.board.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {


    @Query(value = "SELECT c FROM Comments c JOIN fetch c.board")
    List<Comments> findCommentsByBoard_seq(String input_board_title);



}
