package net.avh4.scratch.todomvc;

import android.widget.Button;
import android.widget.EditText;
import com.squareup.otto.Bus;
import net.avh4.scratch.todomvc.view.event.SubmitNewTodo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class TodomvcActivityTest {

    private TodomvcActivity subject;
    @Mock private Bus bus;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ActivityController<TodomvcActivity> controller = Robolectric.buildActivity(TodomvcActivity.class);
        subject = controller.get();
        subject.setBus(bus);
        controller.create();
    }

    @Test
    public void tappingAdd_submitsTheNewTodo() throws Exception {
        EditText field = (EditText) subject.findViewById(R.id.newTodoField);
        field.setText("have cake");

        Button addButton = (Button) subject.findViewById(R.id.add);
        addButton.performClick();

        verify(bus).post(new SubmitNewTodo("have cake"));
    }
}
