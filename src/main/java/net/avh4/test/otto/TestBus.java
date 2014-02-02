package net.avh4.test.otto;

import com.squareup.otto.ThreadEnforcer;

public class TestBus extends com.squareup.otto.Bus {
    public TestBus() {
        super(ThreadEnforcer.ANY);
    }
}
