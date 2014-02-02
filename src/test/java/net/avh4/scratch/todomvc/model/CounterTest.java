package net.avh4.scratch.todomvc.model;

import edu.emory.mathcs.backport.java.util.Arrays;
import net.avh4.scratch.todomvc.model.event.AddedTodo;
import net.avh4.scratch.todomvc.model.event.TodoCount;
import net.avh4.test.otto.TestBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.stub;

public class CounterTest {

    @Mock private TodoModel model;
    @Mock private Todo t1;
    @Mock private Todo t2;
    private Counter subject;
    private TestBus bus;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bus = new TestBus();
        subject = new Counter(model, bus);
    }

    @Test
    public void producesCount() throws Exception {
        stub(model.getTodos()).toReturn(list(t1, t2));
        assertThat(subject.getCount(), is(new TodoCount(2)));
    }

    @Test
    public void onAdd_updatesCount() throws Exception {
        stub(model.getTodos()).toReturn(list(t1));
        bus.post(new AddedTodo(t1));
        bus.verify(new TodoCount(1));
    }

    private <T> List<T> list(T... ts) {
        return Arrays.asList(ts);
    }
}
