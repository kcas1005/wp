package com.example.lecture_spring_2_crudproject.repository.board;

import com.example.lecture_spring_2_crudproject.entity.data.FileUploadEntity;
import org.springframework.data.repository.CrudRepository;

//FileUploadEntity를 저장하는 인터페이스(JPA CrudRepository활용)
public interface FileUploadInfoRepository extends CrudRepository<FileUploadEntity, Long> {

    
    //findBy : 튜플을 찾겠다
    //BoardSeq : BoardSeq 컬럼에 데이터를 찾겠다 
    FileUploadEntity findByBoardSeq(Long boardSeq);


}
