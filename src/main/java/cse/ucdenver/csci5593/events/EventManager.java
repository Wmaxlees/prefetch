package cse.ucdenver.csci5593.events;

import java.util.HashMap;
import java.util.List;

// TODO: Add ordering to the event handlers

/**
 * Created by max on 3/14/16.
 */
public class EventManager {
    private static HashMap<EventType, List<EventHandler> > handlers;

    /**
     * Registers an event handler for the event type specified. The handler's
     * handleEvent method will be called any time the EventType type is
     * generated. Handler must be unregistered with unregisterCallback for the
     * connection to be terminated.
     *
     * @param type The type of event the event handler should be notified of
     * @param handler The object that will handle the events when registered
     * @see unregisterCallback
     */
    public static void registerCallback(EventType type, EventHandler handler) {
        if (handlers.containsKey(type) && !handlers.get(type).contains(handler)) {
            handlers.get(type).add(handler);
        }
    }

    /**
     * Unregisters an event handler for the event type specified.
     *
     * @param type The type of event the event handler should be notified of
     * @param handler The object that will be unregistered
     */
    public static void unregisterCallback(EventType type, EventHandler handler) {
        if (handlers.containsKey(type) && !handlers.get(type).contains(handler)) {
            handlers.get(type).remove(handler);
        }
    }

    /**
     * Calls all handlers attached to the given event type with the given
     * arguments
     *
     * @param type The type of event to call
     * @param args The arguments passed to the handler
     */
    public static void event(EventType type, Object... args) {
        if (handlers.containsKey(type)) {
            for (EventHandler h : handlers.get(type)) {
                h.handleEvent(type, args);
            }
        }
    }
}
