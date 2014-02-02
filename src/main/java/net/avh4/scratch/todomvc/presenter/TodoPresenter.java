package net.avh4.scratch.todomvc.presenter;

import net.avh4.scratch.todomvc.view.TodoView;

import javax.inject.Inject;

@Deprecated
class TodoPresenter {
    private final TodoView view;

    @Inject
    public TodoPresenter(TodoView view) {
        this.view = view;
    }

    public void init() {
        view.setMainVisible(false);
        view.setFooterVisible(false);
    }
}
