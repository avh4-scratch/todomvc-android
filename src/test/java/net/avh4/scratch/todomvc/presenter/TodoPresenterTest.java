package net.avh4.scratch.todomvc.presenter;

import net.avh4.scratch.todomvc.view.TodoView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class TodoPresenterTest {

    private TodoPresenter subject;
    @Mock private TodoView view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new TodoPresenter(view);
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
}
