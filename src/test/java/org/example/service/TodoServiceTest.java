package org.example.service;

import org.example.model.TodoEntitiy;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void add() {
        when(this.todoRepository.save(any(TodoEntitiy.class))).then(AdditionalAnswers.returnsFirstArg());

        TodoRequest expected = new TodoRequest();
        expected.setTitle("Test Title");

        TodoEntitiy actual = this.todoService.add(expected);

        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    void searchById() {
        TodoEntitiy entitiy = new TodoEntitiy();
        entitiy.setId(123L);
        entitiy.setTitle("TITLE");
        entitiy.setOrder(0L);
        entitiy.setCompleted(false);
        Optional<TodoEntitiy> optional = Optional.of(entitiy);

        given(this.todoRepository.findById(anyLong())).willReturn(optional);

        TodoEntitiy actual = this.todoService.searchById(123L);
        TodoEntitiy expected = optional.get();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getOrder(),actual.getOrder());
        assertEquals(expected.getCompleted(), actual.getCompleted());
    }

    @Test
    public void searchByIdFailed(){
        given(this.todoRepository.findById(anyLong()))
                .willReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () ->{
            this.todoService.searchById(123L);
        });
    }
}