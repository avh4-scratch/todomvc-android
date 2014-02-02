package net.avh4.scratch.todomvc;

import com.squareup.otto.Bus;
import net.avh4.util.di.magnum.MagnumDI;

public abstract class OttoMagnumActivity extends MagnumActivity {
    protected Bus bus;

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    protected void onPause() {
        bus.unregister(this);
        super.onPause();
    }

    @Override
    protected void inject(MagnumDI magnum) {
        bus = magnum.get(Bus.class);
    }
}
