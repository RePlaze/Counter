package com.example.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CounterRepository extends JpaRepository<Counter, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Counter c SET c.value = c.value + 1 WHERE c.id = :id")
    void incrementCounter(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Counter c SET c.value = :#{#counter.value}, c.updateHistory = :#{#counter.updateHistory} WHERE c.id = :#{#counter.id}")
    void updateCounter(Counter counter);

    @Query("SELECT c.value FROM Counter c WHERE c.id = :id")
    int getCounterValue(Long id);
}
