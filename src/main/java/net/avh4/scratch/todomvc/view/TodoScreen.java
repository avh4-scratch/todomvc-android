package net.avh4.scratch.todomvc.view;

import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.event.HiddenCheck;
import net.avh4.scratch.todomvc.view.event.UpdateCounts;

public interface TodoScreen {
    void todoCollection(TodoCollection e);

    void updateCounts(UpdateCounts e);

    void clearTodoEntryField();

    void updateCompleteAll(HiddenCheck state);

    void hideFooter(boolean hidden);

    void hideMain(boolean hidden);
}
