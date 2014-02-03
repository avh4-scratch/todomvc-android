package net.avh4.scratch.todomvc;

import com.squareup.otto.Bus;
import net.avh4.scratch.ViewModel;
import net.avh4.util.di.magnum.MagnumDI;

public abstract class OttoMagnumActivity extends MagnumActivity {
    protected Bus bus;
    private ViewModel.M viewModel;

    @Override
    protected void inject(MagnumDI magnum) {
        bus = magnum.get(Bus.class);
        viewModel = magnum.get(ViewModel.M.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
        viewModel.register(this);
    }

    @Override
    protected void onPause() {
        bus.unregister(this);
        super.onPause();
    }
}
