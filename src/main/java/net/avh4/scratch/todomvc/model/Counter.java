package net.avh4.scratch.todomvc.model;

import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;
import net.avh4.scratch.todomvc.model.event.AddedTodo;
import net.avh4.scratch.todomvc.model.event.TodoCount;

import javax.inject.Inject;

public class Counter {
    private final TodoCollection model;
    private final Bus bus;

    @Inject
    public Counter(TodoCollection model, Bus bus) {
        this.model = model;
        this.bus = bus;
        bus.register(this);
    }

    @Produce
    public TodoCount getCount() {
        return new TodoCount(model.getTodos().size());
    }

    @Subscribe
    public void addedTodo(AddedTodo ignored) {
        bus.post(getCount());
    }
}
