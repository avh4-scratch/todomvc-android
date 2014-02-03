package net.avh4.scratch.todomvc.view.event;

@Deprecated
public class UpdateCompleteAll {
    private final HiddenCheck checked;

    public UpdateCompleteAll(HiddenCheck checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "UpdateCompleteAll{" +
                "checked=" + checked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateCompleteAll that = (UpdateCompleteAll) o;

        if (checked != that.checked) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return checked != null ? checked.hashCode() : 0;
    }

    public HiddenCheck getChecked() {
        return checked;
    }
}
