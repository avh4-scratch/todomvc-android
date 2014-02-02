package net.avh4.scratch.todomvc;

import android.widget.Button;
import android.widget.EditText;
import net.avh4.scratch.todomvc.view.event.ClearTodoEntryField;
import net.avh4.scratch.todomvc.view.event.SubmitNewTodo;
import net.avh4.test.otto.TestBus;
import net.avh4.util.di.magnum.MagnumDI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class TodomvcActivityTest {

    private TodomvcActivity subject;
    private TestBus bus;

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

    private EditText newTodoField() {
        return (EditText) subject.findViewById(R.id.newTodoField);
    }
}
