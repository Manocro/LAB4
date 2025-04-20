# LAB4

In this repo, I take my previous Lab 2 which already uses designs as Simple Factory in its modal section and Strategy in sorting. 


I want to implement both an Observer Design and a Command Pattern. By applying the Observer pattern, Ill be able to decouple the event model from its UI so that any change in an event’s state (for example, marking it complete) automatically notifies and updates all registered display panels without manual refresh logic, and by adopting the Command pattern we wrap each user action (like “complete this event”) into its own object, simplifying button handlers, enabling features such as undo/redo, and making the codebase more modular, testable, and easy to extend.