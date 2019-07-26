package palma.model.graphic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import palma.controller.FXMLController;

import java.util.ArrayList;
import java.util.List;

public class SelectionHandler {
    private Clipboard clipboard;

    private EventHandler<MouseEvent> mousePressedEventHandler;

    public SelectionHandler(final Parent root) {
        this.clipboard = new Clipboard();
        this.mousePressedEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SelectionHandler.this.doOnMousePressed(root, event);
                event.consume();
            }
        };
    }

    public EventHandler<MouseEvent> getMousePressedEventHandler() {
        return mousePressedEventHandler;
    }

    private void doOnMousePressed(Parent root, MouseEvent event) {
        Node target = (Node) event.getTarget();
        if(target.equals(root))
            clipboard.unselectAll();
        if(root.getChildrenUnmodifiable().contains(target) && target instanceof SelectableNode) {
            SelectableNode selectableTarget = (SelectableNode) target;
            if(!clipboard.getSelectedItems().contains(selectableTarget))
                clipboard.unselectAll();
            clipboard.select(selectableTarget, true);
        }
    }


    public static class Clipboard {
        private static ObservableList<SelectableNode> selectedItems = FXCollections.observableArrayList();

        public static ObservableList<SelectableNode> getSelectedItems() {
            return selectedItems;
        }

        public boolean select(SelectableNode n, boolean selected) {
            if(n.requestSelection(selected)) {
                if (selected) {
                    selectedItems.add(n);

                    if(n instanceof MyButton) {
                        FXMLController.referenceIDPublic.setText(String.valueOf(((MyButton) n).ID));
                        FXMLController.referenceNazwaPublic.setText(String.valueOf(((MyButton) n).defaultName));
                    }
                    else {
                        FXMLController.referenceIDPublic.setText(String.valueOf(((MyCircle) n).ID));
                        FXMLController.referenceNazwaPublic.setText(String.valueOf(((MyCircle) n).defaultName));
                    }

                } else {
                    selectedItems.remove(n);
                    FXMLController.referenceIDPublic.setText("ID");
                    FXMLController.referenceNazwaPublic.setText("Nazwa");

                }
                n.notifySelection(selected);
                return true;
            } else {
                return false;
            }
        }

        public void unselectAll() {
            List<SelectableNode> unselectList = new ArrayList<>();
            unselectList.addAll(selectedItems);

            for (SelectableNode sN : unselectList) {
                select(sN, false);
            }
        }
    }
}