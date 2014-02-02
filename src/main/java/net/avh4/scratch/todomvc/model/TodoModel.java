package net.avh4.scratch.todomvc.model;

import com.squareup.otto.Bus;
import net.avh4.scratch.todomvc.model.event.AddedTodo;

import java.util.ArrayList;
import java.util.List;

public class TodoModel {
    private final Bus bus;
    private ArrayList<Todo> items = new ArrayList<>();

    public TodoModel(Bus bus) {
        this.bus = bus;
    }

    public void addTodo(String text) {
        Todo todo = new Todo(text);
        items.add(todo);
        bus.post(new AddedTodo(todo));
    }

    public List<Todo> getTodos() {
        return items;
    }
}
