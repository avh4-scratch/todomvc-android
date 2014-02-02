package net.avh4.scratch.todomvc.presenter;

import net.avh4.scratch.todomvc.model.TodoModel;
import net.avh4.scratch.todomvc.view.TodoView;

import javax.inject.Inject;

public class TodoPresenter {
    private final TodoView view;
    private final TodoModel model;

    @Inject
    public TodoPresenter(TodoView view, TodoModel model) {
        this.view = view;
        this.model = model;
    }

    public void init() {
        view.setMainVisible(false);
        view.setFooterVisible(false);
    }

    public void submitNewTodo(String text) {
        text = text.trim();
        if (!text.isEmpty()) {
            model.addTodo(text);
        }
    }
}
