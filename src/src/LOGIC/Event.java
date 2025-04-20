package LOGIC;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class for all events, now observable.
 */
public abstract class Event implements Comparable<Event> {
    protected String name;
    protected LocalDateTime dateTime;

    // ─── OBSERVER SUPPORT ────────────────────────────────────────────────
    private final List<EventListener> listeners = new ArrayList<>();

    /** Register a listener to be notified on changes */
    public void addListener(EventListener l) {
        listeners.add(l);
    }

    /** Remove a previously registered listener */
    public void removeListener(EventListener l) {
        listeners.remove(l);
    }

    /** Notify all listeners of a change */
    protected void notifyListeners() {
        for (EventListener l : listeners) {
            l.onEventChanged(this);
        }
    }
    // ─────────────────────────────────────────────────────────────────────

    public Event(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    /** Must be implemented by subclasses to return the display name */
    public abstract String getName();

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        notifyListeners();
    }

    public void setName(String name) {
        this.name = name;
        notifyListeners();
    }

    @Override
    public int compareTo(Event other) {
        return this.dateTime.compareTo(other.getDateTime());
    }
}
