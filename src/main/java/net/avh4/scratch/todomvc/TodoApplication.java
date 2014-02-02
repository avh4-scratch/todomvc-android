package net.avh4.scratch.todomvc;

import android.app.Application;
import net.avh4.scratch.todomvc.model.Counter;
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
        magnum.get(Counter.class);
        magnum.get(NewTodo.class);
    }

    public MagnumDI getMagnum() {
        return magnum;
    }
}
