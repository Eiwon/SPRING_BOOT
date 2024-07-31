package com.mokcoding.mysite.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.mokcoding.mysite.domain.Reply;

public interface ReplyRepository extends ListCrudRepository<Reply, Integer>{
	List<Reply> findByBoardId(int boardId);
}
