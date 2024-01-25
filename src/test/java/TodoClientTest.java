import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import todo.Todo;
import todo.TodoClient;
import todo.TodoNotFoundException;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoClientTest {

    // System Under Test (sut)
    TodoClient client = new TodoClient();

    @Test
    void findAll() throws IOException, InterruptedException {
        List<Todo> todos = client.findAll();
        assertEquals(200, todos.size());
    }

    @Test
    void shouldReturnTodoGivenValidId() throws IOException, InterruptedException, TodoNotFoundException {
        Todo todo = client.findById(1);
        assertEquals(1, todo.userId());
        assertEquals(1, todo.id());
        assertEquals("delectus aut autem", todo.title());
        assertFalse(todo.completed());
    }


    @Test
    void shouldThrowNotFoundExceptionGivenInvalidId() {
        TodoNotFoundException todoNotFoundException = assertThrows(TodoNotFoundException.class, () -> client.findById(999));
        assertEquals("todo.Todo not found", todoNotFoundException.getMessage());
    }

    @Test
    void shouldCreateNewTodo() throws IOException, InterruptedException {
        Todo todo = new Todo(201,1,"Learn Java",false);
        HttpResponse<String> response = client.create(todo);
        assertEquals(201, response.statusCode());
    }

    @Test
    void shouldUpdateExistingTodo() throws IOException, InterruptedException, TodoNotFoundException {
        Todo todo = new Todo(1,1,"NEW TITLE", true);
        HttpResponse<String> response = client.update(todo);
        assertEquals(200, response.statusCode());
    }

    @Test
    void shouldDeleteExistingTodo() throws IOException, InterruptedException, TodoNotFoundException {
        Todo todo = client.findById(1);
        HttpResponse<String> response = client.delete(todo);
        assertEquals(200, response.statusCode());
    }

}