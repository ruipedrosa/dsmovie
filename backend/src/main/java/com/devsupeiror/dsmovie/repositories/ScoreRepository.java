package com.devsupeiror.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsupeiror.dsmovie.entities.Score;
import com.devsupeiror.dsmovie.entities.ScorePK;

public interface  ScoreRepository extends JpaRepository<Score,ScorePK>{

	
}
