package net.avh4.scratch.todomvc.presenter;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.event.UpdateCounts;

public class DisplayCounts {
    private final Bus bus;

    public DisplayCounts(Bus bus) {
        this.bus = bus;
        bus.register(this);
    }

    @Subscribe
    public void todoCollection(TodoCollection e) {
        bus.post(new UpdateCounts(e.getTodosCount()));
    }
}
