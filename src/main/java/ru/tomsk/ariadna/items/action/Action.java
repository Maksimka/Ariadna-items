package ru.tomsk.ariadna.items.action;

/**
 * Действие.
 *
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public interface Action {

    /**
     * Выполнить дейтсвие.
     */
    void redo();

    /**
     * Отменить действие.
     */
    void undo();
}
