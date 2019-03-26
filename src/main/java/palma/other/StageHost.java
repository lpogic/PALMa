package palma.other;

import javafx.fxml.FXMLLoader;

public interface StageHost {
    FXMLLoader getPaneLoader(String forPath);
    OpenStage openStage(Object key);
    OpenStage popUpStage(String paneKey, Object... bundle);
    void collectStage(Object key);
}
