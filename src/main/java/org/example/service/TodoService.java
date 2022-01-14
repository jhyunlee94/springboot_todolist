package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.TodoEntitiy;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

//    1 todo 리스트 목록에 아이템을 추가
//    2 todo 리스트 목록 중 특정 아이템을 조회
//    3 todo 리스트 전체 목록을 조회
//    4 todo 리스트 목록 중 특정 아이템을 수정
//    5 todo 리스트 목록 중 특정 아이템을 삭제
//    6 todo 리스트 전체 목록을 삭제

    public TodoEntitiy add(TodoRequest request){
        TodoEntitiy todoEntitiy = new TodoEntitiy();
        todoEntitiy.setTitle(request.getTitle());
        todoEntitiy.setOrder(request.getOrder());
        todoEntitiy.setCompleted(request.getCompleted());
        return this.todoRepository.save(todoEntitiy); // repository로 데이터들어감
    }

    public TodoEntitiy searchById(Long id){
        return this.todoRepository.findById(id) //값이 없으면은 notfound exception을 날려줄게요
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<TodoEntitiy> searchAll(){ //퀵픽트 옵션+엔터
        return this.todoRepository.findAll();
    }

    public TodoEntitiy updateById(Long id, TodoRequest request){
        TodoEntitiy todoEntitiy = this.searchById(id);
        if(request.getTitle() != null){
            todoEntitiy.setTitle(request.getTitle());
        }
        if(request.getOrder() != null){
            todoEntitiy.setOrder(request.getOrder());
        }
        if(request.getCompleted() != null){
            todoEntitiy.setCompleted(request.getCompleted());
        }
        return this.todoRepository.save(todoEntitiy);
    }

    public void deleteById(Long id){
        this.todoRepository.deleteById(id);
    }

    public void deleteAll(){
        this.todoRepository.deleteAll();
    }
}
