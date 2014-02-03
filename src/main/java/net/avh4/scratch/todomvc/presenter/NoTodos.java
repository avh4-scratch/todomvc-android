package net.avh4.scratch.todomvc.presenter;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.TodoScreen;
import net.avh4.scratch.todomvc.view.event.HideMain;

public class NoTodos {
    private final TodoScreen view;

    public NoTodos(Bus bus, TodoScreen view) {
        this.view = view;
        bus.register(this);
    }

    @Subscribe
    public void todoCollection(TodoCollection e) {
        view.hideFooter(e.getTodosCount() == 0);
        view.hideMain(new HideMain(e.getTodosCount() == 0));
    }
}
