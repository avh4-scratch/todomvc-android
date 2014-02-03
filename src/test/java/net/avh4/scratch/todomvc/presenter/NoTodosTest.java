package net.avh4.scratch.todomvc.presenter;

import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.event.HideFooter;
import net.avh4.scratch.todomvc.view.event.HideMain;
import net.avh4.test.otto.TestBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.stub;

public class NoTodosTest {

    @Mock private TodoCollection collection;
    private TestBus bus;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bus = new TestBus();
        new NoTodos(bus);
    }

    @Test
    public void withNoTodos_hidesFooter() throws Exception {
        stub(collection.getTodosCount()).toReturn(0);
        bus.post(collection);
        bus.verify(new HideFooter(true));
    }

    @Test
    public void withSomeTodos_showsFooter() throws Exception {
        stub(collection.getTodosCount()).toReturn(1);
        bus.post(collection);
        bus.verify(new HideFooter(false));
    }

    @Test
    public void withNoTodos_hidesMain() throws Exception {
        stub(collection.getTodosCount()).toReturn(0);
        bus.post(collection);
        bus.verify(new HideMain(true));
    }

    @Test
    public void withSomeTodos_showsMain() throws Exception {
        stub(collection.getTodosCount()).toReturn(1);
        bus.post(collection);
        bus.verify(new HideMain(false));
    }
}
