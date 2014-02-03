package net.avh4.scratch.todomvc.view;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.squareup.otto.Subscribe;
import net.avh4.scratch.todomvc.OttoMagnumActivity;
import net.avh4.scratch.todomvc.R;
import net.avh4.scratch.todomvc.model.Todo;
import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.event.*;
import net.avh4.util.di.magnum.MagnumDI;

public class TodomvcActivity extends OttoMagnumActivity {

    private EditText newTodoField;
    private TextView totalCountLabel;
    private ListView listView;
    private ArrayAdapter<Todo> adapter;
    private CheckBox completeAll;

    @Override
    protected void inject(MagnumDI magnum) {
        super.inject(magnum);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listView = (ListView) findViewById(R.id.list_view);
        adapter = new ArrayAdapter<>(this, R.layout.cell_todo, android.R.id.text1);
        listView.setAdapter(adapter);

        totalCountLabel = (TextView) findViewById(R.id.totalCount);
        newTodoField = (EditText) findViewById(R.id.new_todo_field);
        Button addButton = (Button) findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bus.post(new SubmitNewTodo(newTodoField.getText().toString()));
            }
        });

        completeAll = (CheckBox) findViewById(R.id.complete_all);
        completeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                bus.post(new ToggleAllComplete(checked));
            }
        });
    }

    @Subscribe
    public void todoCollection(TodoCollection e) {
        adapter.clear();
        adapter.addAll(e.getTodos());
    }

    @Subscribe
    public void todoCount(UpdateCounts e) {
        totalCountLabel.setText(e.getTotal() + " items");
    }

    @Subscribe
    public void clearTodoEntryField(ClearTodoEntryField e) {
        newTodoField.setText("");
    }

    @Subscribe
    public void updateCompleteAll(UpdateCompleteAll e) {
        if (e.getChecked() == HiddenCheck.HIDDEN) {
            completeAll.setVisibility(View.GONE);
        } else {
            completeAll.setVisibility(View.VISIBLE);
        }
        if (e.getChecked() == HiddenCheck.CHECKED) {
            completeAll.setChecked(true);
        } else {
            completeAll.setChecked(false);
        }
    }

    @Subscribe
    public void hideFooter(HideFooter e) {
        int visibility;
        if (e.isHidden()) {
            visibility = View.GONE;
        } else {
            visibility = View.VISIBLE;
        }
        totalCountLabel.setVisibility(visibility);
    }

    @Subscribe
    public void hideMain(HideMain e) {
        int visibility;
        if (e.isHidden()) {
            visibility = View.GONE;
        } else {
            visibility = View.VISIBLE;
        }
        listView.setVisibility(visibility);
        completeAll.setVisibility(visibility);
    }
}