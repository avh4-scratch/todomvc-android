package net.avh4.scratch.todomvc.view.event;

@Deprecated
public class HideMain {
    private final boolean hidden;

    public HideMain(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isHidden() {
        return hidden;
    }

    @Override
    public String toString() {
        return "HideMain{" +
                "hidden=" + hidden +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HideMain hideMain = (HideMain) o;

        if (hidden != hideMain.hidden) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (hidden ? 1 : 0);
    }
}
