package com.polar_moviechart.userservice.repository.movie;

import com.polar_moviechart.userservice.domain.entity.movie.MovieLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieLikeRepository extends JpaRepository<MovieLike, Long> {
    Optional<MovieLike> findByCodeAndUserId(int code, Long userId);

    Optional<MovieLike> findByUserIdAndCode(Long userId, int code);

    Page<MovieLike> findByUserId(Long userId, PageRequest pageable);

    Integer countByCode(int code);

    Page<MovieLike> findByUserIdAndCodeIn(Long userId, List<Integer> movieCodes, PageRequest pageable);

    Page<MovieLike> findByUserIdAndLikeStatus(Long userId, boolean isLike, PageRequest pageable);
}
