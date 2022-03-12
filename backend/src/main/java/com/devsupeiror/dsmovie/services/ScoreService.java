package com.devsupeiror.dsmovie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsupeiror.dsmovie.dto.MovieDTO;
import com.devsupeiror.dsmovie.dto.ScoreDTO;
import com.devsupeiror.dsmovie.entities.Movie;
import com.devsupeiror.dsmovie.entities.Score;
import com.devsupeiror.dsmovie.entities.User;
import com.devsupeiror.dsmovie.repositories.MovieRepository;
import com.devsupeiror.dsmovie.repositories.ScoreRepository;
import com.devsupeiror.dsmovie.repositories.UserRepository;

@Service
public class ScoreService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ScoreRepository scoreRepository;
		
	@Transactional
	public MovieDTO saveCore(ScoreDTO dto) {
		User user = userRepository.findByEmail(dto.getEmail());
		if (user == null) {
			user = new User();
			user.setEmail(dto.getEmail());
			user = userRepository.saveAndFlush(user);		
		}
		
		Movie movie = movieRepository.findById(dto.getMovieId()).get();
		
		Score score = new Score();
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(dto.getScore());
		
		score = scoreRepository.saveAndFlush(score);
	
		double sum = 0;
		for (Score s : movie.getScores()) {
			sum = sum + s.getValue();
		}
		
		double avg = sum / movie.getScores().size();
		
		movie.setScore(avg);
		movie.setCount(movie.getScores().size());
		
		movie = movieRepository.save(movie);
		
		return new MovieDTO(movie);
	}
}
