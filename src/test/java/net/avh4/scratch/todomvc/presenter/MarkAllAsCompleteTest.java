package net.avh4.scratch.todomvc.presenter;

import com.squareup.otto.Bus;
import net.avh4.scratch.todomvc.model.Todo;
import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.TodoScreen;
import net.avh4.scratch.todomvc.view.event.HiddenCheck;
import net.avh4.scratch.todomvc.view.event.ToggleAllComplete;
import net.avh4.scratch.todomvc.view.event.UpdateCompleteAll;
import net.avh4.test.otto.TestBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class MarkAllAsCompleteTest {
    private Bus bus;
    @Mock private TodoCollection model;
    @Mock private Todo t1;
    @Mock private Todo t2;
    @Mock private TodoScreen view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bus = new TestBus();
        new MarkAllAsComplete(bus, model, view);
    }

    @Test
    public void withNoTodos_hidesCompleteAll() throws Exception {
        stub(model.getTodos()).toReturn(Arrays.<Todo>asList());
        bus.post(model);
        verify(view).updateCompleteAll(new UpdateCompleteAll(HiddenCheck.HIDDEN));
    }

    @Test
    public void whenOnlyTodoIsChecked_becomesChecked() throws Exception {
        stub(t1.isComplete()).toReturn(true);
        stub(model.getTodos()).toReturn(Arrays.asList(t1));
        bus.post(model);
        verify(view).updateCompleteAll(new UpdateCompleteAll(HiddenCheck.CHECKED));
    }

    @Test
    public void whenOnlyTodoIsNotChecked_becomesUnchecked() throws Exception {
        stub(t1.isComplete()).toReturn(false);
        stub(model.getTodos()).toReturn(Arrays.asList(t1));
        bus.post(model);
        verify(view).updateCompleteAll(new UpdateCompleteAll(HiddenCheck.UNCHECKED));
    }

    @Test
    public void whenSomeTodosAreNotChecked_becomesUnchecked() throws Exception {
        stub(t1.isComplete()).toReturn(true);
        stub(t2.isComplete()).toReturn(false);
        stub(model.getTodos()).toReturn(Arrays.asList(t1, t2));
        bus.post(model);
        verify(view).updateCompleteAll(new UpdateCompleteAll(HiddenCheck.UNCHECKED));
    }

    @Test
    public void completeAll_marksAllAsComplete() throws Exception {
        bus.post(new ToggleAllComplete(true));
        verify(model).completeAll();
    }

    @Test
    public void uncheckAll_marksAllAsIncomplete() throws Exception {
        bus.post(new ToggleAllComplete(false));
        verify(model).uncheckAll();
    }
}
