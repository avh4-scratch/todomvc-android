package net.avh4.scratch.todomvc.view.event;

@Deprecated
public class UpdateCounts {
    private final int total;

    public UpdateCounts(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "UpdateCounts{" +
                "total=" + total +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateCounts that = (UpdateCounts) o;

        if (total != that.total) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return total;
    }

    public int getTotal() {
        return total;
    }
}
