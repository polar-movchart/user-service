package com.polar_moviechart.userservice.domain.repository.movie;

import com.polar_moviechart.userservice.domain.entity.movie.MovieLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieLikeRepository extends JpaRepository<MovieLike, Long> {
    Optional<MovieLike> findByCodeAndUserId(int code, Long userId);
}
