package com.example.sample;

import com.example.sample.model.entity.CommentEntity;
import com.example.sample.model.entity.LoginEntity;
import com.example.sample.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@SpringBootTest
class SampleApplicationTests {

	@Autowired
	CommentService commentService;

	@Test
	void contextLoads() {
	}

	@Test
	void findAll(){
		System.out.println("Test");
		ArrayList<CommentEntity> dtos=commentService.findAll();
		System.out.println(dtos);
	}
	@Test
	@Transactional //실행후 롤백해줌
	void insert(){
		CommentEntity entity = new CommentEntity(null,3,"name3","comment3");
		CommentEntity entitySaved=commentService.save(entity);

		System.out.println("Test");
		ArrayList<CommentEntity> dtos=commentService.findAll();
		System.out.println(dtos);
	}
	@Test
	void select(){
		CommentEntity dto=commentService.findById(22);
		System.out.println(dto);
	}
	@Test
	void update(){
		CommentEntity entity = new CommentEntity(3,3,"name3U","comment3U");
		CommentEntity entitySaved=commentService.save(entity);

		System.out.println("Test");
		ArrayList<CommentEntity> dtos=commentService.findAll();
		System.out.println(dtos);
	}
	@Test
	void delete(){
		//CommentEntity entity = new CommentEntity(41,null,null,null);

		commentService.delete(41);
		System.out.println("Test");
		ArrayList<CommentEntity> dtos=commentService.findAll();
		System.out.println(dtos);

//		// 예상
//		String title = "라라라라";
//		String content = "4444";
//		ArticleForm dto = new ArticleForm(4L, title, content);
//		Article expected = null;
//		// 실제
//		Article article = articleService.create(dto);
//		// 비교
//		assertEquals(expected, article);
	}


}
