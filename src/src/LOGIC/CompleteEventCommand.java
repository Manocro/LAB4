package LOGIC;

/**
 * A Command that marks a Completable event as complete.
 */
public class CompleteEventCommand implements Command {
    private final Completable event;

    public CompleteEventCommand(Completable event) {
        this.event = event;
    }

    @Override
    public void execute() {
        event.complete();
    }
}
