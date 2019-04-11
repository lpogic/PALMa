package palma.core;

import java.net.URL;

public interface StageHost {
    URL getResource(String forPath);
    OpenStage openStage(Object key);
    OpenStage popUpStage(String paneKey, Object... bundle);
    void collectStage(Object key);
}
