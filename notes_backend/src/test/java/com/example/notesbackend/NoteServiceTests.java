package com.example.notesbackend;

import com.example.notesbackend.dto.NoteCreateRequest;
import com.example.notesbackend.dto.NoteUpdateRequest;
import com.example.notesbackend.dto.PagedResponse;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class NoteServiceTests {

    @Autowired
    private NoteService service;

    @Test
    void createAndGetUpdateDeleteFlow() {
        NoteCreateRequest create = new NoteCreateRequest().setTitle("First").setContent("Hello");
        NoteResponse created = service.create(create);
        assertThat(created.getId()).isNotBlank();
        assertThat(created.getTitle()).isEqualTo("First");

        NoteResponse fetched = service.getById(created.getId());
        assertThat(fetched.getTitle()).isEqualTo("First");

        NoteUpdateRequest update = new NoteUpdateRequest().setTitle("Updated").setContent("World");
        NoteResponse updated = service.update(created.getId(), update);
        assertThat(updated.getTitle()).isEqualTo("Updated");

        PagedResponse<NoteResponse> page = service.list(0, 10, "Upd");
        assertThat(page.getItems()).extracting(NoteResponse::getId).contains(updated.getId());

        service.delete(created.getId());
        assertThatThrownBy(() -> service.getById(created.getId())).isInstanceOf(RuntimeException.class);
    }
}
