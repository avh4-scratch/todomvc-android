package net.avh4.scratch.todomvc;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import net.avh4.scratch.todomvc.model.Todo;
import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.event.ClearTodoEntryField;
import net.avh4.scratch.todomvc.view.event.SubmitNewTodo;
import net.avh4.scratch.todomvc.view.event.ToggleAllComplete;
import net.avh4.test.otto.TestBus;
import net.avh4.util.di.magnum.MagnumDI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.stub;

@RunWith(RobolectricTestRunner.class)
public class TodomvcActivityTest {

    private TodomvcActivity subject;
    private TestBus bus;
    @Mock private TodoCollection collection;
    @Mock private Todo t1;
    @Mock private Todo t2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bus = new TestBus();
        ActivityController<TodomvcActivity> controller = Robolectric.buildActivity(TodomvcActivity.class);
        subject = controller.create().get();
        subject.inject(new MagnumDI(bus));
        controller.start().resume();
    }

    @Test
    public void tappingAdd_submitsTheNewTodo() throws Exception {
        newTodoField().setText("have cake");

        Button addButton = (Button) subject.findViewById(R.id.add);
        addButton.performClick();

        bus.verify(new SubmitNewTodo("have cake"));
    }

    @Test
    public void testClearEntryField() throws Exception {
        newTodoField().setText("should be cleared");
        bus.post(new ClearTodoEntryField());
        assertThat(newTodoField().getText().toString(), is(""));
    }

    @Test
    public void shouldShowTodos() throws Exception {
        stub(collection.getTodos()).toReturn(Arrays.asList(t1, t2));
        bus.post(collection);
        ListView listView = (ListView) subject.findViewById(R.id.list_view);
        View item = listView.getAdapter().getView(0, null, null);
        assertThat(item, notNullValue());
    }

    @Test
    public void togglingCompleteAll_sendsEvent() throws Exception {
        CheckBox check = (CheckBox) subject.findViewById(R.id.complete_all);
        check.performClick();
        bus.verify(new ToggleAllComplete(true));
    }

    private EditText newTodoField() {
        return (EditText) subject.findViewById(R.id.new_todo_field);
    }
}
