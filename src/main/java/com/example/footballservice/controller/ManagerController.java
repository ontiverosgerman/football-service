package com.example.footballservice.controller;

import com.example.footballservice.model.Manager;
import com.example.footballservice.model.Team;
import com.example.footballservice.repository.ManagerRepository;
import com.example.footballservice.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/managers")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerRepository managerRepository;
    private final TeamRepository teamRepository;

    // GET all managers
    @GetMapping
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    // GET manager by ID
    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable Long id) {
        return managerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST a new manager and assign to team
    @PostMapping
    public ResponseEntity<?> createManager(@RequestBody Manager manager) {
        if (manager.getTeam() != null && manager.getTeam().getId() != null) {
            Optional<Team> team = teamRepository.findById(manager.getTeam().getId());
            if (team.isPresent()) {
                manager.setTeam(team.get());
            } else {
                return ResponseEntity.badRequest().body("Team not found with ID: " + manager.getTeam().getId());
            }
        }
        return ResponseEntity.ok(managerRepository.save(manager));
    }

    // DELETE manager
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable Long id) {
        if (managerRepository.existsById(id)) {
            managerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
