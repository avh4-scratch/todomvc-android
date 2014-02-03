package net.avh4.scratch.todomvc;

import android.app.Application;
import net.avh4.scratch.todomvc.presenter.DisplayCounts;
import net.avh4.scratch.todomvc.presenter.MarkAllAsComplete;
import net.avh4.scratch.todomvc.presenter.NewTodo;
import net.avh4.util.di.magnum.MagnumDI;

public class TodoApplication extends Application {
    private MagnumDI magnum;

    @Override
    public void onCreate() {
        super.onCreate();

        magnum = new MagnumDI();
        magnum = ApplicationModule.configure(magnum);
        instantiateFeatures();
    }

    private void instantiateFeatures() {
        magnum.get(NewTodo.class);
        magnum.get(DisplayCounts.class);
        magnum.get(MarkAllAsComplete.class);
    }

    public MagnumDI getMagnum() {
        return magnum;
    }
}
