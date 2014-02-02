package net.avh4.scratch.todomvc.presenter;

import net.avh4.scratch.todomvc.model.TodoModel;
import net.avh4.scratch.todomvc.view.TodoView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class TodoPresenterTest {

    private TodoPresenter subject;
    @Mock private TodoView view;
    @Mock private TodoModel model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new TodoPresenter(view, model);
    }

    @Test
    public void initialState_hidesMain() throws Exception {
        subject.init();
        verify(view).setMainVisible(false);
    }

    @Test
    public void initialState_hidesFooter() throws Exception {
        subject.init();
        verify(view).setFooterVisible(false);
    }

    @Test
    public void submitNewTodo_addsIt() throws Exception {
        subject.submitNewTodo("Eat cake");
        verify(model).addTodo("Eat cake");
    }

    @Test
    public void submitNewTodo_withExtraWhiteSpace_trimsBeforeAdding() throws Exception {
        subject.submitNewTodo("  have cake  ");
        verify(model).addTodo("have cake");
    }

    @Test
    public void submitNewTodo_withEmptyString_doesNotAdd() throws Exception {
        subject.submitNewTodo("");
        verifyZeroInteractions(model);
    }

    @Test
    public void submitNewTodo_withOnlyWhitespace_doesNotAdd() throws Exception {
        subject.submitNewTodo("    ");
        verifyZeroInteractions(model);
    }
}
