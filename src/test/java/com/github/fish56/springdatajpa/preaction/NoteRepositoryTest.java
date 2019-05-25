package com.github.fish56.springdatajpa.preaction;

import com.github.fish56.springdatajpa.SpringDataJpaImplApplicationTests;
import com.github.fish56.springdatajpa.preaction.Note;
import com.github.fish56.springdatajpa.preaction.NoteRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class NoteRepositoryTest extends SpringDataJpaImplApplicationTests {
    @Autowired
    private NoteRepository noteRepository;

    /**
     * 从打印结果中可以看到确实触发了PrePersist
     *   并设置了创建日期和更新日期
     */
    @Test
    public void save(){
        Note note = new Note();
        note.setContent("Hell world");

        noteRepository.save(note);
        System.out.println(note);
    }

    /**
     * 可看到，更新数据时，触发了on
     * @throws Exception
     */
    @Test
    public void update() throws Exception{
        // 初始化一个记录
        Note note = new Note();
        note.setContent("Hello world");
        noteRepository.save(note);

        // 两秒后修改记录
        Thread.sleep(2000);
        note.setContent("Hi");
        noteRepository.save(note);

        // 获得新的记录
        Optional<Note> noteOptional = noteRepository.findById(1L);
        System.out.println(noteOptional.get());
        // 可以看到时间确实更新了
    }
}