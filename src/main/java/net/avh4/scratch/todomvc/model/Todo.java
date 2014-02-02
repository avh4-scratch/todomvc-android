package net.avh4.scratch.todomvc.model;

public class Todo {
    private final String text;

    public Todo(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (text != null ? !text.equals(todo.text) : todo.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }
}
