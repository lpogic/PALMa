package palma;

import javafx.stage.Stage;
import palma.core.OpenRoot;

public class PALMaApplication extends OpenRoot {

    @Override
    public void employ(Stage primaryStage) {

        primaryStage.setTitle("palma");
        openStage(primaryStage).openScene("welcome").show();
    }
}

