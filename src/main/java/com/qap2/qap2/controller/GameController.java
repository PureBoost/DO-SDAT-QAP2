package com.qap2.qap2.controller;

import com.qap2.qap2.model.Game;
import com.qap2.qap2.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping
    public List<Game> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Game create(@RequestBody Game game) {
        return service.save(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> update(@PathVariable Long id, @RequestBody Game updated) {
        return service.getById(id).map(g -> {
            g.setTitle(updated.getTitle());
            g.setGenre(updated.getGenre());
            g.setPlatform(updated.getPlatform());
            g.setPrice(updated.getPrice());
            return ResponseEntity.ok(service.save(g));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}