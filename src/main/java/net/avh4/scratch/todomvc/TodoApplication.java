package net.avh4.scratch.todomvc;

import android.app.Application;
import dagger.ObjectGraph;
import net.avh4.scratch.todomvc.model.Counter;
import net.avh4.scratch.todomvc.presenter.NewTodo;

import javax.inject.Inject;

public class TodoApplication extends Application {
    private ObjectGraph dagger;
    @Inject Counter counter;
    @Inject NewTodo newTodo;

    @Override
    public void onCreate() {
        super.onCreate();

        dagger = ObjectGraph.create(ApplicationModule.class);
        dagger.inject(this);
    }

    public ObjectGraph getDagger() {
        return dagger;
    }
}
