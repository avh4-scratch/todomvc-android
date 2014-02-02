package net.avh4.scratch.todomvc.model.event;

import net.avh4.scratch.todomvc.model.Todo;

public class AddedTodo {
    private final Todo todo;

    public AddedTodo(Todo todo) {
        this.todo = todo;
    }

    @Override
    public String toString() {
        return "AddedTodo{" +
                "todo=" + todo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddedTodo addedTodo = (AddedTodo) o;

        if (todo != null ? !todo.equals(addedTodo.todo) : addedTodo.todo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return todo != null ? todo.hashCode() : 0;
    }
}
