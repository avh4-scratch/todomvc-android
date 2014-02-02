package net.avh4.scratch.todomvc.presenter;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import net.avh4.scratch.todomvc.model.TodoModel;
import net.avh4.scratch.todomvc.view.event.ClearTodoEntryField;
import net.avh4.scratch.todomvc.view.event.SubmitNewTodo;

import javax.inject.Inject;

public class NewTodo {
    private final Bus bus;
    private final TodoModel model;

    @Inject
    public NewTodo(Bus bus, TodoModel model) {
        this.bus = bus;
        this.model = model;
        bus.register(this);
    }

    @Subscribe
    public void submitNewTodo(SubmitNewTodo e) {
        String text = e.getText().trim();
        if (!text.isEmpty()) {
            model.addTodo(text);
        }
        bus.post(new ClearTodoEntryField());
    }
}