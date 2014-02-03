package net.avh4.scratch.todomvc.presenter;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.event.HideFooter;
import net.avh4.scratch.todomvc.view.event.HideMain;

public class NoTodos {
    private final Bus bus;

    public NoTodos(Bus bus) {
        this.bus = bus;
        bus.register(this);
    }

    @Subscribe
    public void todoCollection(TodoCollection e) {
        bus.post(new HideFooter(e.getTodosCount() == 0));
        bus.post(new HideMain(e.getTodosCount() == 0));
    }
}
