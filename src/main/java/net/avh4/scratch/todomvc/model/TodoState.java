package net.avh4.scratch.todomvc.model;

public enum TodoState {
    TODO, COMPLETED;

    public boolean isComplete() {
        return this == COMPLETED;
    }
}
