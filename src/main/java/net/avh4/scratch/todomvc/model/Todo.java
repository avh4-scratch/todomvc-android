package net.avh4.scratch.todomvc.model;

public class Todo {
    private final String text;
    private TodoState state;

    public Todo(String text) {
        this.text = text;
        this.state = TodoState.TODO;
    }

    public Todo(String text, TodoState state) {
        this.text = text;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "text='" + text + '\'' +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (state != todo.state) return false;
        if (text != null ? !text.equals(todo.text) : todo.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }

    public TodoState getState() {
        return state;
    }

    public boolean isComplete() {
        return getState().isComplete();
    }

    public void complete() {
        state = TodoState.COMPLETED;
    }

    public void uncheck() {
        state = TodoState.TODO;
    }
}
