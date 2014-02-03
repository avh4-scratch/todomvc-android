package net.avh4.scratch;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ViewModel {
    public interface M {
        void register(Object listener);
    }

    public static M create(final Class<?>... interfaces) {
        ArrayList<Class<?>> interfaces2 = new ArrayList<>(Arrays.asList(interfaces));
        interfaces2.add(M.class);
        return (M) Proxy.newProxyInstance(ViewModel.class.getClassLoader(),
                interfaces2.toArray(new Class[interfaces2.size()]), new InvocationHandler() {
            private final HashMap<Class<?>, Object> listeners = new HashMap<>();
            private final HashMap<Method, Object[]> lastCalls = new HashMap<>();

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (method.getName().equals("register")) {
                    Object listener = args[0];
                    register(listener);
                    return null;
                }

                if (method.getDeclaringClass() == interfaces[0]) {
                    sendToListener(interfaces[0], method, args);
                    saveCall(method, args);
                    return null;
                }

                return null;
            }

            private void saveCall(Method method, Object[] args) {
                lastCalls.put(method, args);
            }

            private void sendToListener(Class<?> anInterface, Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
                Object listener = listeners.get(anInterface);
                if (listener != null)
                    method.invoke(listener, args);
            }

            private void register(Object listener) throws InvocationTargetException, IllegalAccessException {
                Class<?> anInterface = interfaces[0];
                assert anInterface.isInstance(listener);
                listeners.put(anInterface, listener);
                setLastCallsToListener(listener, anInterface);
            }

            private void setLastCallsToListener(Object listener, Class<?> anInterface) throws IllegalAccessException, InvocationTargetException {
                for (Method method : anInterface.getMethods()) {
                    if (lastCalls.containsKey(method)) {
                        method.invoke(listener, lastCalls.get(method));
                    }
                }
            }
        });
    }
}
