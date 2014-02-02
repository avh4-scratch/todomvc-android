package net.avh4.test.otto;

import com.squareup.otto.ThreadEnforcer;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

public class TestBus extends com.squareup.otto.Bus {
    private final ArrayList<Object> events = new ArrayList<>();

    public TestBus() {
        super(ThreadEnforcer.ANY);
    }

    @Override
    public void post(Object event) {
        events.add(event);
        super.post(event);
    }

    public void verify(Object event) {
        assertThat(events, hasItem(event));
    }
}
