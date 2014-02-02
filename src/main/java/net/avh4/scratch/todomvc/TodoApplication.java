package net.avh4.scratch.todomvc;

import android.app.Application;
import com.squareup.otto.Bus;
import net.avh4.scratch.todomvc.model.Counter;
import net.avh4.scratch.todomvc.model.TodoModel;
import net.avh4.scratch.todomvc.presenter.NewTodo;

public class TodoApplication extends Application {
    private Bus bus;

    @Override
    public void onCreate() {
        super.onCreate();

        this.bus = new Bus();
        TodoModel model = new TodoModel(bus);
        new Counter(model, bus);
        new NewTodo(bus, model);
    }

    public Bus getBus() {
        return bus;
    }
}
