package LOGIC;

/**
 * A Command that marks a Completable event as complete, storing its previous state.
 */
public class CompleteEventCommand implements Command {
    private final Completable event;
    private final boolean prevState;

    public CompleteEventCommand(Completable event) {
        this.event      = event;
        this.prevState  = event.isComplete();
    }

    @Override
    public void execute() {
        event.complete();
    }

    @Override
    public void undo() {
        // restore the prior completion state
        if (event instanceof Deadline d) {
            d.setComplete(prevState);
        } else if (event instanceof Meeting m) {
            m.setComplete(prevState);
        }
    }
}