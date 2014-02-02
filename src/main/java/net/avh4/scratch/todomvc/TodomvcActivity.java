package net.avh4.scratch.todomvc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import net.avh4.scratch.todomvc.model.event.TodoCount;
import net.avh4.scratch.todomvc.view.event.ClearTodoEntryField;
import net.avh4.scratch.todomvc.view.event.SubmitNewTodo;

import javax.inject.Inject;

public class TodomvcActivity extends Activity {

    @Inject Bus bus;
    private EditText newTodoField;
    private TextView totalCountLabel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TodoApplication) getApplication()).getDagger().inject(this);
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

    @Override
    protected void onResume() {
        super.onStart();
        bus.register(this);
    }

    @Override
    protected void onPause() {
        bus.unregister(this);
        super.onPause();
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