package ru.tomsk.ariadna.items.item;

import java.awt.BorderLayout;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tomsk.ariadna.items.PersistenceUtil;
import ru.tomsk.ariadna.items.data.Item;
import ru.tomsk.ariadna.items.data.Type;

/**
 *
 * @author Шаймарданов Максим Маратович <maximax@contek.ru>
 */
public class TypeListBox extends JPanel {

    private static final Logger logger = LoggerFactory.getLogger(TypeListBox.class);

    public TypeListBox(final ItemTable itemTable) {
        super(new BorderLayout());
        final JList typeList = new JList();
        typeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        typeList.setLayoutOrientation(JList.VERTICAL);
        typeList.setListData(getTypeList().toArray());
        typeList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                Type type = (Type) typeList.getSelectedValue();
                ItemTableModel dataModel = getItemTableModel(type);
                itemTable.setModel(dataModel);
            }

            private ItemTableModel getItemTableModel(Type type) {
                EntityManager entityManager = PersistenceUtil.getEntityManager();
                Query query = entityManager.createQuery(
                        "SELECT i FROM Item i "
                        + "WHERE i.model.modelPK.type = :type "
                        + "ORDER BY i.number ");
                query.setParameter("type", type.toString());
                List<Item> items = query.getResultList();
                return new ItemTableModel(items);
            }
        });
        JLabel typeLabel = new JLabel("Тип снаряжения", JLabel.CENTER);
        add(typeLabel, BorderLayout.NORTH);
        add(new JScrollPane(typeList), BorderLayout.CENTER);
    }

    public List<Type> getTypeList() {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        TypedQuery<Type> allTypeQuery = entityManager.createNamedQuery("Type.findAll", Type.class);
        return allTypeQuery.getResultList();
    }
}
