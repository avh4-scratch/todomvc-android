package net.avh4.scratch.todomvc.model.event;

public class TodoCount {
    private final int count;

    public TodoCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "TodoCount{" +
                "count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TodoCount todoCount = (TodoCount) o;

        if (count != todoCount.count) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return count;
    }
}
