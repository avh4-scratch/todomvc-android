package net.avh4.scratch.todomvc.view;

import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.event.HideMain;
import net.avh4.scratch.todomvc.view.event.UpdateCompleteAll;
import net.avh4.scratch.todomvc.view.event.UpdateCounts;

public interface TodoScreen {
    void todoCollection(TodoCollection e);

    void updateCounts(UpdateCounts e);

    void clearTodoEntryField();

    void updateCompleteAll(UpdateCompleteAll e);

    void hideFooter(boolean hidden);

    void hideMain(HideMain e);
}
