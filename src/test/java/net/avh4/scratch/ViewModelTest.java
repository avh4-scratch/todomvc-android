package net.avh4.scratch;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

public class ViewModelTest {

    @Mock private A aListener;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldExtendInterface() throws Exception {
        ViewModel.M subject = ViewModel.create(A.class);
        assertThat(subject, instanceOf(A.class));
    }

    @Test
    public void interfaceMethod_withListener_shouldCallListener() throws Exception {
        ViewModel.M subject = ViewModel.create(A.class);
        subject.register(aListener);

        ((A) subject).setButtonVisible(true);

        verify(aListener).setButtonVisible(true);
    }

    @Test
    public void register_withPreviousCall_shouldCallListener() throws Exception {
        ViewModel.M subject = ViewModel.create(A.class);
        ((A) subject).setButtonVisible(true);

        subject.register(aListener);

        verify(aListener).setButtonVisible(true);
    }

    public static interface A {
        void setButtonVisible(boolean visible);
    }
}
