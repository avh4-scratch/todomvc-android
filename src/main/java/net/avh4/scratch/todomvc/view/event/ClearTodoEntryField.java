package net.avh4.scratch.todomvc.view.event;

public class ClearTodoEntryField {
    @Override
    public boolean equals(Object o) {
        if (o instanceof ClearTodoEntryField) return true;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
