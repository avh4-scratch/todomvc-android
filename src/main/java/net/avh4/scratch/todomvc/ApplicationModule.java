package net.avh4.scratch.todomvc;

import com.squareup.otto.Bus;
import net.avh4.scratch.todomvc.model.TodoModel;
import net.avh4.util.di.magnum.MagnumDI;

class ApplicationModule {
    public static MagnumDI configure(MagnumDI magnum) {
        Bus bus = new Bus();
        return magnum.add(
                bus,
                new TodoModel(bus)
        );
    }
}
