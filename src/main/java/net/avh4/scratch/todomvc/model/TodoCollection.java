package net.avh4.scratch.todomvc.model;

import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import net.avh4.scratch.todomvc.model.event.AddedTodo;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TodoCollection {
    private final Bus bus;
    private final ArrayList<Todo> items = new ArrayList<>();

    @Inject
    public TodoCollection(Bus bus) {
        this.bus = bus;
        bus.register(this);
    }

    public void addTodo(String text) {
        Todo todo = new Todo(text);
        items.add(todo);
        bus.post(new AddedTodo(todo));
        bus.post(this);
    }

    public List<Todo> getTodos() {
        return items;
    }

    @Produce
    public TodoCollection todoCollection() {
        return this;
    }
}
