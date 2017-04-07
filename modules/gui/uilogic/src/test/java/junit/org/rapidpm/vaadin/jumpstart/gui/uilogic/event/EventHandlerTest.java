package junit.org.rapidpm.vaadin.jumpstart.gui.uilogic.event;

import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.event.EventBus;


public class EventHandlerTest {

  public static final int FIRE_NR_EVENTS = 100;

  @Test
  public void test001() throws Exception {

    TestHandlerClass testHandlerClass = new TestHandlerClass();
    TestHandlerClass testHandlerClass2 = new TestHandlerClass();
    EventBus.register(testHandlerClass);

    for(int i = 0; i < FIRE_NR_EVENTS; i++){
      EventBus.fireSynchronousEvent(new TestEvent());
    }
     EventBus.register(testHandlerClass2);

    for(int i = 0; i < FIRE_NR_EVENTS; i++){
      EventBus.fireSynchronousEvent(new SecondTestEvent());
    }


    Assert.assertEquals(FIRE_NR_EVENTS * 2, testHandlerClass.getCallCount());
    Assert.assertEquals(FIRE_NR_EVENTS, testHandlerClass2.getCallCount());
  }

  @Test
  public void test002() throws Exception {
    TestHandlerClass testHandlerClass = new TestHandlerClass();
    TestHandlerClass testHandlerClass2 = new TestHandlerClass();
    EventBus.register(testHandlerClass);

    for(int i = 0; i < FIRE_NR_EVENTS; i++){
      EventBus.fireAsyncEvent(new TestEvent());
    }
    EventBus.register(testHandlerClass2);

    for(int i = 0; i < FIRE_NR_EVENTS; i++){
      EventBus.fireAsyncEvent(new SecondTestEvent());
    }
    Thread.sleep(100);
    Assert.assertEquals(FIRE_NR_EVENTS * 2, testHandlerClass.getCallCount());
    Assert.assertEquals(FIRE_NR_EVENTS, testHandlerClass2.getCallCount());
  }
}
