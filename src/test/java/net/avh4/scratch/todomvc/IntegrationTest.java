package net.avh4.scratch.todomvc;

import android.view.View;
import android.widget.*;
import net.avh4.scratch.todomvc.view.TodomvcActivity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class IntegrationTest {
    private TodomvcActivity activity;

    @Before
    public void setUp() throws Exception {
        ActivityController<TodomvcActivity> controller = Robolectric.buildActivity(TodomvcActivity.class);
        activity = controller.create().start().resume().get();
    }

    @Test
    public void testAddingTodos() throws Exception {
        newTodoField().setText("Eat cake");
        addButton().performClick();
        assertThat(newTodoField().getText().toString(), is(""));
        newTodoField().setText("Have cake");
        addButton().performClick();
        assertThat(totalCountLabel().getText().toString(), is("2 items"));
    }

    @Test
    public void testCompleteAll() throws Exception {
        assertThat(completeAllCheckbox().getVisibility(), is(View.GONE));
        newTodoField().setText("Eat cake");
        addButton().performClick();
        assertThat(completeAllCheckbox().getVisibility(), is(View.VISIBLE));
        newTodoField().setText("Have cake");
        addButton().performClick();
        assertThat(completeAllCheckbox().isChecked(), is(false));

        completeAllCheckbox().performClick();
        assertThat(getCellText(0).getText().toString(), containsString("COMPLETED"));
        assertThat(getCellText(1).getText().toString(), containsString("COMPLETED"));
        assertThat(completeAllCheckbox().isChecked(), is(true));

        newTodoField().setText("Eat carrots");
        addButton().performClick();
        assertThat(completeAllCheckbox().isChecked(), is(false));
    }

    @Test
    public void testHideFooter() throws Exception {
        assertThat(totalCountLabel().getVisibility(), is(View.GONE));
        newTodoField().setText("Have cake");
        addButton().performClick();
        assertThat(totalCountLabel().getVisibility(), is(View.VISIBLE));
    }

    @Test
    public void testHideMain() throws Exception {
        assertThat(listView().getVisibility(), is(View.GONE));
        newTodoField().setText("Have cake");
        addButton().performClick();
        assertThat(listView().getVisibility(), is(View.VISIBLE));
    }

    private TextView getCellText(int index) {
        return (TextView) listView().getAdapter().getView(index, null, null).findViewById(android.R.id.text1);
    }

    private ListView listView() {
        return (ListView) activity.findViewById(R.id.list_view);
    }

    private CheckBox completeAllCheckbox() {
        return (CheckBox) activity.findViewById(R.id.complete_all);
    }

    private TextView totalCountLabel() {
        return (TextView) activity.findViewById(R.id.totalCount);
    }

    private Button addButton() {
        return (Button) activity.findViewById(R.id.add);
    }

    private EditText newTodoField() {
        return (EditText) activity.findViewById(R.id.new_todo_field);
    }
}
