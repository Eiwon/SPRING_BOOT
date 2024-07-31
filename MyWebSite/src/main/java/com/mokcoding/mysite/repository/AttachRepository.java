package com.mokcoding.mysite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.mokcoding.mysite.domain.Attach;

public interface AttachRepository extends ListCrudRepository<Attach, Integer>{
	List<Attach> findByBoardId(Integer boardId);
	void deleteByBoardId(Integer boardId);
	
    @Query("SELECT a FROM Attach a WHERE a.attachPath = :dateString")
    List<Attach> findByAttachPath(@Param("dateString") String dateString);
	
}
