package ru.tomsk.ariadna.items.action;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import ru.tomsk.ariadna.items.PersistenceUtil;
import ru.tomsk.ariadna.items.data.Delivery;
import ru.tomsk.ariadna.items.data.DeliveryPacket;

/**
 * Выдать снаряжение.
 *
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class GiveItemsAction implements Action {

    private final int packetId;

    private final int[] itemIds;

    /**
     * Конструирует комаду выдать снаряжение.
     *
     * @param packetId пакет выдачи
     * @param itemIds снаряжения неоходимое выдать
     */
    public GiveItemsAction(int packetId, int[] itemIds) {
        this.packetId = packetId;
        this.itemIds = itemIds;
    }

    @Override
    public void redo() {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        entityManager.getTransaction().begin();
        for (int itemId : itemIds) {
            Delivery delivery = new Delivery(packetId, itemId);
            entityManager.persist(delivery);
        }
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
