package net.avh4.scratch.todomvc.presenter;

import com.squareup.otto.Bus;
import net.avh4.scratch.todomvc.model.TodoModel;
import net.avh4.scratch.todomvc.view.SubmitNewTodo;
import net.avh4.test.otto.TestBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class NewTodoTest {

    @Mock private TodoModel model;
    private Bus bus;
    private NewTodo subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bus = new TestBus();
        subject = new NewTodo(bus, model);
    }

    @Test
    public void addsNewTodoToModel() throws Exception {
        bus.post(new SubmitNewTodo("Eat cake"));
        verify(model).addTodo("Eat cake");
    }

    @Test
    public void submitNewTodo_withExtraWhiteSpace_trimsBeforeAdding() throws Exception {
        bus.post(new SubmitNewTodo("  have cake  "));
        verify(model).addTodo("have cake");
    }

    @Test
    public void submitNewTodo_withEmptyString_doesNotAdd() throws Exception {
        bus.post(new SubmitNewTodo(""));
        verifyZeroInteractions(model);
    }

    @Test
    public void submitNewTodo_withOnlyWhitespace_doesNotAdd() throws Exception {
        bus.post(new SubmitNewTodo("    "));
        verifyZeroInteractions(model);
    }
}
