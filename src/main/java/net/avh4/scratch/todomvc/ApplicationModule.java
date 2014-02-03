package net.avh4.scratch.todomvc;

import com.squareup.otto.Bus;
import net.avh4.scratch.ViewModel;
import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.TodoScreen;
import net.avh4.util.di.magnum.MagnumDI;

class ApplicationModule {
    public static MagnumDI configure(MagnumDI magnum) {
        Bus bus = new Bus();
        return magnum.add(
                bus,
                new TodoCollection(bus),
                ViewModel.create(TodoScreen.class)
        );
    }
}
