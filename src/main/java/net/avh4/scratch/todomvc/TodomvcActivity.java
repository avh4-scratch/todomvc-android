package net.avh4.scratch.todomvc;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.squareup.otto.Subscribe;
import net.avh4.scratch.todomvc.model.Todo;
import net.avh4.scratch.todomvc.model.TodoCollection;
import net.avh4.scratch.todomvc.view.event.ClearTodoEntryField;
import net.avh4.scratch.todomvc.view.event.SubmitNewTodo;
import net.avh4.scratch.todomvc.view.event.UpdateCounts;

public class TodomvcActivity extends OttoMagnumActivity {

    private EditText newTodoField;
    private TextView totalCountLabel;
    private ListView listView;
    private ArrayAdapter<Todo> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, R.layout.cell_todo, android.R.id.text1);
        listView.setAdapter(adapter);

        totalCountLabel = (TextView) findViewById(R.id.totalCount);
        newTodoField = (EditText) findViewById(R.id.newTodoField);
        Button addButton = (Button) findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bus.post(new SubmitNewTodo(newTodoField.getText().toString()));
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
}