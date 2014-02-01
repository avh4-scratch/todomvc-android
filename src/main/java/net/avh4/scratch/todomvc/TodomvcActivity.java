package net.avh4.scratch.todomvc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class TodomvcActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}