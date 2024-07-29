package com.mokcoding.mysite.persistence;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.mokcoding.mysite.domain.Attach;

public interface AttachRepository extends ListCrudRepository<Attach, Integer>{

	List<Attach> findByBoardId(Integer boardId);
	
	void deleteByBoardId(Integer boardId); 
	
}
