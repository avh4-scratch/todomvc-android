package net.avh4.scratch.todomvc.view.event;

public class ToggleAllComplete {
    private final boolean complete;

    public ToggleAllComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isComplete() {
        return complete;
    }

    @Override
    public String toString() {
        return "ToggleAllComplete{" +
                "complete=" + complete +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToggleAllComplete that = (ToggleAllComplete) o;

        if (complete != that.complete) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (complete ? 1 : 0);
    }
}
