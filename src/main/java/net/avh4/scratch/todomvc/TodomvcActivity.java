package net.avh4.scratch.todomvc;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.squareup.otto.Subscribe;
import net.avh4.scratch.todomvc.model.Todo;
import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.event.*;

public class TodomvcActivity extends OttoMagnumActivity {

    private EditText newTodoField;
    private TextView totalCountLabel;
    private ListView listView;
    private ArrayAdapter<Todo> adapter;
    private CheckBox completeAll;

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
}