package net.avh4.scratch.todomvc.presenter;

import com.squareup.otto.Bus;
import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.TodoScreen;
import net.avh4.scratch.todomvc.view.event.SubmitNewTodo;
import net.avh4.test.otto.TestBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class NewTodoTest {

    @Mock private TodoCollection model;
    @Mock private TodoScreen view;
    private Bus bus;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bus = new TestBus();
        new NewTodo(bus, model, view);
    }

    @Test
    public void addsNewTodoToModel() throws Exception {
        bus.post(new SubmitNewTodo("Eat cake"));
        verify(model).addTodo("Eat cake");
    }

    @Test
    public void clearsEntryField() throws Exception {
        bus.post(new SubmitNewTodo("anything"));
        verify(view).clearTodoEntryField();
    }

    @Test
    public void withExtraWhiteSpace_trimsBeforeAdding() throws Exception {
        bus.post(new SubmitNewTodo("  have cake  "));
        verify(model).addTodo("have cake");
    }

    @Test
    public void withEmptyString_doesNotAdd() throws Exception {
        bus.post(new SubmitNewTodo(""));
        verifyZeroInteractions(model);
    }

    @Test
    public void withOnlyWhitespace_doesNotAdd() throws Exception {
        bus.post(new SubmitNewTodo("    "));
        verifyZeroInteractions(model);
    }
}
