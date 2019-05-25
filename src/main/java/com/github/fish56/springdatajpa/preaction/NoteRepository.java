package com.github.fish56.springdatajpa.preaction;

import com.github.fish56.springdatajpa.preaction.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
