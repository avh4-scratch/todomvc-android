package net.avh4.scratch.todomvc.view;

import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.event.*;

public interface TodoScreen {
    void todoCollection(TodoCollection e);

    void updateCounts(UpdateCounts e);

    void clearTodoEntryField(ClearTodoEntryField e);

    void updateCompleteAll(UpdateCompleteAll e);

    void hideFooter(HideFooter e);

    void hideMain(HideMain e);
}
