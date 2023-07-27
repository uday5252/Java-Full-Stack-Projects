package com.abc.Streaming.application.project.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.Streaming.application.project.ENTITIES.LikeEntity;
import com.abc.Streaming.application.project.REPOSITORIES.LikeInterfaceRepositary;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class LikeService {
	@Autowired
	EntityManager em;

	@Autowired
	LikeInterfaceRepositary l;
	
	@Transactional
	public LikeEntity addlike(LikeEntity le) {
		// TODO Auto-generated method stub
		LikeEntity saved = l.save(le);
		em.refresh(saved);
		
		System.out.println(saved);
		return saved;
	}

	public void Unlike(int uid) {
		// TODO Auto-generated method stub
		l.delete(l.findByUserId(uid));
	}

}
