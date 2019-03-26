package palma.other;

import javafx.stage.Stage;

public interface PaneHost {
    OpenPane openPane(String key);
    OpenPane openPane(String key, boolean read, boolean save);
    void setMainPane(String key);
    void setMainPane(OpenPane openPane, boolean stackRecord);
    void popPane();
    Stage getStage();
}
