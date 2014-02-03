package net.avh4.scratch.todomvc.model;

import net.avh4.scratch.todomvc.model.event.AddedTodo;
import net.avh4.test.otto.TestBus;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

public class TodoCollectionTest {

    private TodoCollection subject;
    private TestBus bus;

    @Before
    public void setUp() throws Exception {
        bus = new TestBus();
        subject = new TodoCollection(bus);
    }

    @Test
    public void shouldStartEmpty() throws Exception {
        assertThat(subject.getTodos().size(), is(0));
    }

    @Test
    public void testAddingATodo() throws Exception {
        subject.addTodo("Eat cake");
        assertThat(subject.getTodos(), contains(new Todo("Eat cake")));
    }

    @Test
    public void add_shouldPostAddTodoEvent() throws Exception {
        subject.addTodo("Eat carrots");
        bus.verify(new AddedTodo(new Todo("Eat carrots")));
    }

    @Test
    public void add_shouldPostCollectionEvent() throws Exception {
        subject.addTodo("Eat crackers");
        bus.verify(subject);
    }

    @Test
    public void testGetTodoCount() throws Exception {
        assertThat(subject.getTodosCount(), is(0));
        subject.addTodo("Have cake");
        assertThat(subject.getTodosCount(), is(1));
    }

    @Test
    public void completeAll_completesAllTodos() throws Exception {
        subject.addTodo("Eat carrots");
        subject.addTodo("Eat cake");
        subject.completeAll();
        assertThat(subject.getTodos(), everyItem(isComplete()));
    }

    @Test
    public void uncheckAll_unchecksAllTodos() throws Exception {
        subject.addTodo("Eat carrots");
        subject.addTodo("Have carrots");
        subject.getTodos().get(0).complete();

        subject.uncheckAll();
        assertThat(subject.getTodos(), everyItem(isNotComplete()));
    }

    private static Matcher<Todo> isComplete() {
        return new TypeSafeMatcher<Todo>() {
            @Override
            protected boolean matchesSafely(Todo item) {
                return item.isComplete();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("a completed todo");
            }
        };
    }

    private static Matcher<Todo> isNotComplete() {
        return new TypeSafeMatcher<Todo>() {
            @Override
            protected boolean matchesSafely(Todo item) {
                return !item.isComplete();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("a completed todo");
            }
        };
    }
}
