package ru.tomsk.ariadna.items.action;

import java.util.LinkedList;

/**
 *
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class ActionManager {

    private static final ActionManager INSTANCE = new ActionManager();

    private final LinkedList<Action> actions;

    private ActionManager() {
        actions = new LinkedList<Action>();
    }

    public static ActionManager getInstance() {
        return INSTANCE;
    }

    public void redo() {

    }

    public void undo(){

    }
}
