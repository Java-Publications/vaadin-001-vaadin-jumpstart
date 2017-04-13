package junit.org.rapidpm.vaadin.jumpstart.gui.uilogic.event;

import java.util.concurrent.atomic.AtomicInteger;

import org.rapidpm.vaadin.jumpstart.gui.uilogic.event.anotations.HandleEvent;

public class TestHandlerClass {
    AtomicInteger callCount = new AtomicInteger(0);

    @HandleEvent
    public void handleTestEvent(TestEvent event) {
        int i = callCount.incrementAndGet();
    }

    @HandleEvent
    public void handleSeccondTestEvent(SecondTestEvent event) {
        callCount.incrementAndGet();

    }

    public int getCallCount() {
        return callCount.get();
    }
}
