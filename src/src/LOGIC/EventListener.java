package LOGIC;


//Observer interface for event model changes.
public interface EventListener {
    /**
     *Will be called when an Event has changed (e.g., its completion status).
     */

    void onEventChanged(Event event);
}

