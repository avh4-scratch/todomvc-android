package net.avh4.scratch.todomvc.presenter;

import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.event.UpdateCounts;
import net.avh4.test.otto.TestBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.stub;

public class DisplayCountsTest {
    @Mock private TodoCollection collection;
    private TestBus bus;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bus = new TestBus();
        new DisplayCounts(bus);
    }

    @Test
    public void whenTodosAreUpdates_countsNumberOfItems() throws Exception {
        stub(collection.getTodosCount()).toReturn(77);
        bus.post(collection);
        bus.verify(new UpdateCounts(77));
    }
}
