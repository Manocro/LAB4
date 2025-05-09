package LOGIC;

import java.time.LocalDateTime;

/**
 * This class represents a deadline event that can be marked complete
 */

public class Deadline extends Event implements Completable {
    private boolean isComplete;

    // Creates a new deadline with name and due date
    public Deadline(String name, LocalDateTime deadline) {
        super(name, deadline);
        this.isComplete = false;
    }

    // Returns the deadline name
    @Override
    public String getName() {
        return name;
    }


    /** Reversible setter used by undo(). */
    public void setComplete(boolean isComplete) {
    this.isComplete = isComplete;
    notifyListeners();
    }
    // Marks the deadline as complete

    @Override
    public void complete() {
        isComplete = true;
        notifyListeners(); //inform any UI panels or other observers about completion status.
    }

    // Returns completion status
    @Override
    public boolean isComplete() {
        return isComplete;
    }
}