package palma.core;

import javafx.stage.Stage;

public interface PaneHost {
    OpenPane openPane(String key);
    OpenPane openPane(String key, boolean read, boolean save);
    OpenPane openPane(String key, PaneModel model);
    OpenPane openPane(String key, PaneModel model, boolean read, boolean save);
    void setMainPane(String key);
    void setMainPane(OpenPane openPane, boolean stackRecord);
    void popMainPane();
    Stage getStage();
}
