package net.avh4.scratch.todomvc.presenter;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import net.avh4.scratch.todomvc.model.Todo;
import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.TodoScreen;
import net.avh4.scratch.todomvc.view.event.HiddenCheck;
import net.avh4.scratch.todomvc.view.event.ToggleAllComplete;
import net.avh4.scratch.todomvc.view.event.UpdateCompleteAll;

import static net.avh4.scratch.todomvc.view.event.HiddenCheck.*;

public class MarkAllAsComplete {
    private final TodoCollection model;
    private final TodoScreen view;

    public MarkAllAsComplete(Bus bus, TodoCollection model, TodoScreen view) {
        this.model = model;
        this.view = view;
        bus.register(this);
    }

    @Subscribe
    public void todo(TodoCollection e) {
        boolean checked = true;
        for (Todo todo : e.getTodos()) {
            if (!todo.isComplete()) {
                checked = false;
                break;
            }
        }
        HiddenCheck checked1;
        if (e.getTodos().size() == 0) {
            checked1 = HIDDEN;
        } else if (checked) {
            checked1 = CHECKED;
        } else {
            checked1 = UNCHECKED;
        }
        view.updateCompleteAll(new UpdateCompleteAll(checked1));
    }

    @Subscribe
    public void toggleAllComplete(ToggleAllComplete e) {
        if (e.isComplete()) {
            model.completeAll();
        } else {
            model.uncheckAll();
        }
    }
}
