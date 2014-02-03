package net.avh4.scratch.todomvc.presenter;

import com.squareup.otto.Bus;
import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.TodoScreen;
import net.avh4.test.otto.TestBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class DisplayCountsTest {
    @Mock private TodoCollection collection;
    @Mock private TodoScreen view;
    private Bus bus;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bus = new TestBus();
        new DisplayCounts(bus, view);
    }

    @Test
    public void whenTodosAreUpdates_countsNumberOfItems() throws Exception {
        stub(collection.getTodosCount()).toReturn(77);
        bus.post(collection);
        verify(view).updateCounts(77);
    }
}
