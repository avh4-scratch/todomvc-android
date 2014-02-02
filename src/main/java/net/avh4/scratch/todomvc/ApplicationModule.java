package net.avh4.scratch.todomvc;

import com.squareup.otto.Bus;
import dagger.Module;
import dagger.Provides;
import net.avh4.scratch.todomvc.model.TodoModel;

import javax.inject.Singleton;

@Module(injects = {TodomvcActivity.class, TodoApplication.class})
public class ApplicationModule {
    @Provides
    @Singleton
    public Bus getBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    public TodoModel getTodoModel(Bus bus) {
        return new TodoModel(bus);
    }
}
