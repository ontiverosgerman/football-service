package com.example.footballservice.controller;

import com.example.footballservice.model.Player;
import com.example.footballservice.model.Team;
import com.example.footballservice.repository.PlayerRepository;
import com.example.footballservice.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    // GET all players
    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // GET player by ID
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        return playerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET all players by team ID
    @GetMapping("/team/{teamId}")
    public List<Player> getPlayersByTeamId(@PathVariable Long teamId) {
        return playerRepository.findByTeamId(teamId);
    }

    // POST a new player and assign to team
    @PostMapping
    public ResponseEntity<?> createPlayer(@RequestBody Player player) {
        if (player.getTeam() != null && player.getTeam().getId() != null) {
            Optional<Team> team = teamRepository.findById(player.getTeam().getId());
            if (team.isPresent()) {
                player.setTeam(team.get());
            } else {
                return ResponseEntity.badRequest().body("Team not found with ID: " + player.getTeam().getId());
            }
        }
        return ResponseEntity.ok(playerRepository.save(player));
    }

    // DELETE player
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
