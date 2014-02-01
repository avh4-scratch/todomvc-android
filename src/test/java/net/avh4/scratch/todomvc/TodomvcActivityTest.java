package net.avh4.scratch.todomvc;

import android.app.Activity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class TodomvcActivityTest {

  @Test
  public void shouldExist() throws Exception {
    Activity activity = Robolectric.buildActivity(TodomvcActivity.class).create().get();
    assertTrue(activity != null);
  }
}
