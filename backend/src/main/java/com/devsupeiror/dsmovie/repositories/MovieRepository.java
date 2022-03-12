package com.devsupeiror.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsupeiror.dsmovie.entities.Movie;

public interface  MovieRepository extends JpaRepository<Movie,Long>{

	
}
