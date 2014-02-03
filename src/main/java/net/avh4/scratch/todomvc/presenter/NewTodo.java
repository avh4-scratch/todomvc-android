package net.avh4.scratch.todomvc.presenter;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.TodoScreen;
import net.avh4.scratch.todomvc.view.event.ClearTodoEntryField;
import net.avh4.scratch.todomvc.view.event.SubmitNewTodo;

import javax.inject.Inject;

public class NewTodo {
    private final TodoCollection model;
    private final TodoScreen view;

    @Inject
    public NewTodo(Bus bus, TodoCollection model, TodoScreen view) {
        this.model = model;
        this.view = view;
        bus.register(this);
    }

    @Subscribe
    public void submitNewTodo(SubmitNewTodo e) {
        String text = e.getText().trim();
        if (!text.isEmpty()) {
            model.addTodo(text);
        }
        view.clearTodoEntryField(new ClearTodoEntryField());
    }
}
