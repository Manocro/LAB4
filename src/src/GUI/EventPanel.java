package GUI;

import LOGIC.Completable;
import LOGIC.Event;
import LOGIC.EventListener;
import LOGIC.Meeting;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

/**
 * Displays a single Event and listens for its changes.
 */
public class EventPanel extends JPanel implements EventListener {
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    private final Event event;
    private JLabel nameLabel;

    public EventPanel(Event event) {
        this.event = event;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder());

        buildInfoPanel();
        addControlButtons();

        // ─── OBSERVER ────────────────────
        event.addListener(this);
        updateCompletionState();
        // ─────────────────────────────────
    }

    private void buildInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        // Create the name label and store it as a field
        nameLabel = new JLabel(event.getName());
        JLabel dateLabel =
                new JLabel("Starts: " + event.getDateTime().format(formatter));

        if (event instanceof Meeting meeting) {
            dateLabel.setText(dateLabel.getText()
                    + " | Duration: " + formatDuration(meeting.getDuration()));
            infoPanel.add(new JLabel("Location: " + meeting.getLocation()));
        }

        infoPanel.add(nameLabel);
        infoPanel.add(dateLabel);
        add(infoPanel, BorderLayout.CENTER);
    }

    private void addControlButtons() {
        JPanel buttonPanel = new JPanel();
        if (event instanceof Completable completable) {
            JButton completeButton = new JButton("Complete");
            completeButton.addActionListener(e -> completable.complete());
            buttonPanel.add(completeButton);
        }
        add(buttonPanel, BorderLayout.EAST);
    }

    /** Called by the Event model when anything changes. */
    @Override
    public void onEventChanged(Event evt) {
        updateCompletionState();
    }

    private String formatDuration(Duration duration) {
        long hours   = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        return String.format("%dh %02dm", hours, minutes);
    }

    private void updateCompletionState() {
        if (event instanceof Completable && ((Completable) event).isComplete()) {
            nameLabel.setText(event.getName() + " ✓");
            nameLabel.setForeground(Color.GRAY);
        } else {
            nameLabel.setText(event.getName());
            nameLabel.setForeground(Color.BLACK);
        }

        // Revalidate and repaint the panel to reflect the changes
        revalidate();
        repaint();
    }
}
