package net.avh4.scratch.todomvc;

import android.app.Activity;
import android.os.Bundle;
import net.avh4.util.di.magnum.MagnumDI;

public abstract class MagnumActivity extends Activity {
    protected abstract void inject(MagnumDI magnum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(((TodoApplication) getApplication()).getMagnum());
    }
}
