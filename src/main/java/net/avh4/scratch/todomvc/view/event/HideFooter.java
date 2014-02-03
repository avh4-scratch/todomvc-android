package net.avh4.scratch.todomvc.view.event;

@Deprecated
public class HideFooter {
    private final boolean hidden;

    public HideFooter(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isHidden() {
        return hidden;
    }

    @Override
    public String toString() {
        return "HideFooter{" +
                "hidden=" + hidden +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HideFooter that = (HideFooter) o;

        if (hidden != that.hidden) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (hidden ? 1 : 0);
    }
}
