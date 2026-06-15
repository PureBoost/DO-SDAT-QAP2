package com.qap2.qap2.service;

import com.qap2.qap2.model.Game;
import com.qap2.qap2.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository repo;

    public GameService(GameRepository repo) {
        this.repo = repo;
    }

    public List<Game> getAll() {
        return repo.findAll();
    }

    public Optional<Game> getById(Long id) {
        return repo.findById(id);
    }

    public Game save(Game game) {
        return repo.save(game);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}