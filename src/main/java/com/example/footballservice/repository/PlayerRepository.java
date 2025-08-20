package com.example.footballservice.repository;

import com.example.footballservice.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeamId(Long teamId); // Optional: find players by team
}
