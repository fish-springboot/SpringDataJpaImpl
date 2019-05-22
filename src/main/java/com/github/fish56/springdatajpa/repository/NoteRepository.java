package com.github.fish56.springdatajpa.repository;

import com.github.fish56.springdatajpa.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
