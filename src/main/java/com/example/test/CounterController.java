package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/counter")
public class CounterController {
    private final CounterRepository counterRepository;

    @Autowired
    public CounterController(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @GetMapping("/{id}")
    public Counter getCounter(@PathVariable Long id) {
        return counterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid counter id: " + id));
    }

    @PutMapping("/{id}/increment")
    public void incrementCounter(@PathVariable Long id) {
        Counter counter = counterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid counter id: " + id));

        counter.setValue(counter.getValue() + 1);
        counter.getUpdateHistory().add(counter.getValue());

        counterRepository.save(counter);
    }

    @PutMapping("/{id}/update")
    public void updateCounter(@PathVariable Long id, @RequestBody Counter updatedCounter) {
        Counter counter = counterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid counter id: " + id));

        counter.setValue(updatedCounter.getValue());
        counter.getUpdateHistory().add(counter.getValue());

        counterRepository.save(counter);
    }

    @GetMapping("/{id}/history")
    public List<Integer> getUpdateHistory(@PathVariable Long id) {
        Counter counter = counterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid counter id: " + id));

        return counter.getUpdateHistory();
    }
}
