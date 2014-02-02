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
import net.avh4.scratch.todomvc.view.event.SubmitNewTodo;

public class TodomvcActivity extends Activity {

    private Bus bus;
    private EditText newTodoField;
    private TextView totalCountLabel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (bus == null) {
            bus = ((TodoApplication) getApplication()).getBus();
        }
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

        bus.register(this);
    }

    public void setBus(Bus bus) {
        if (this.bus != null) throw new RuntimeException("Bus already set");
        this.bus = bus;
    }

    @Subscribe
    public void todoCount(TodoCount e) {
        totalCountLabel.setText(e.getCount() + " items");
    }
}