package net.avh4.scratch.todomvc.presenter;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.TodoScreen;
import net.avh4.scratch.todomvc.view.event.UpdateCounts;

public class DisplayCounts {
    private final TodoScreen view;

    public DisplayCounts(Bus bus, TodoScreen view) {
        this.view = view;
        bus.register(this);
    }

    @Subscribe
    public void todoCollection(TodoCollection e) {
        view.updateCounts(new UpdateCounts(e.getTodosCount()));
    }
}
