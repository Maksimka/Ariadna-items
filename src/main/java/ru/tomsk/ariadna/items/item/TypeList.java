package ru.tomsk.ariadna.items.item;

import java.awt.BorderLayout;
import java.awt.Font;
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
 * @author Ŝajmardanov Maksim <maximaxsh@gmail.com>
 */
public class TypeList extends JPanel {

    private static final Logger logger = LoggerFactory.getLogger(TypeList.class);

    public TypeList(final ItemTable itemTable) {
        super(new BorderLayout());
        final JList typeList = new JList();
        Font font = typeList.getFont();
        typeList.setFont(font.deriveFont(font.getStyle() & ~Font.BOLD));
        typeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        typeList.setLayoutOrientation(JList.VERTICAL);
        typeList.setListData(getTypeList().toArray());
        typeList.setCellRenderer(new TypeListCellRenderer());
        typeList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Type type = (Type) typeList.getSelectedValue();
                List<Item> items = getItems(type);
                itemTable.getModel().setItems(items);
            }

            private List<Item> getItems(Type type) {
                EntityManager entityManager = PersistenceUtil.getEntityManager();
                Query query = entityManager.createQuery(
                        "SELECT i FROM Item i "
                        + "LEFT JOIN i.deliveries as del "
                        + "WHERE i.model.modelPK.type = :type "
                        + "AND (del.isReturn = null OR del.isReturn = true) "
                        + "ORDER BY i.number ");
                query.setParameter("type", type.getName());
                List<Item> items = query.getResultList();
                type.setCacheItemCount(items.size());
                return items;
            }
        });
        JLabel typeLabel = new JLabel("Виды снаряжений", JLabel.CENTER);
        add(typeLabel, BorderLayout.NORTH);
        add(new JScrollPane(typeList), BorderLayout.CENTER);
    }

    private List<Type> getTypeList() {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        TypedQuery<Type> allTypeQuery = entityManager.createNamedQuery(
                "Type.findAllByOrder", Type.class);
        return allTypeQuery.getResultList();
    }
}
