package net.avh4.scratch.todomvc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.squareup.otto.Subscribe;
import net.avh4.scratch.todomvc.model.event.TodoCount;
import net.avh4.scratch.todomvc.view.event.ClearTodoEntryField;
import net.avh4.scratch.todomvc.view.event.SubmitNewTodo;

public class TodomvcActivity extends OttoMagnumActivity {

    private EditText newTodoField;
    private TextView totalCountLabel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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
    public void todoCount(TodoCount e) {
        totalCountLabel.setText(e.getCount() + " items");
    }

    @Subscribe
    public void clearTodoEntryField(ClearTodoEntryField e) {
        newTodoField.setText("");
    }
}