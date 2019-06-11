package palma;

import javafx.stage.Stage;
import palma.core.OpenRoot;
import palma.dealer.*;

public class PALMaApplication extends OpenRoot {

    @Override
    public void employ(Stage primaryStage) {

        openDealer(new LogicDesignDealer());
        openDealer(new LogicDeviceEditorDealer());
        openDealer(new LogicDevicePickerDealer());
        openDealer(new LogicCompilationDealer());
        openDealer(new XMLDealer());

        primaryStage.setTitle("palma");
        openStage(primaryStage).openScene("welcome").show();
    }
}

