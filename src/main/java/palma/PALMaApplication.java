package palma;

import javafx.stage.Stage;
import palma.core.OpenRoot;
import palma.dealer.LogicConnectionDesignDealer;
import palma.dealer.LogicDesignDealer;
import palma.dealer.LogicFunctionDesignDealer;

public class PALMaApplication extends OpenRoot {

    @Override
    public void employ(Stage primaryStage) {

        openDealer(new LogicDesignDealer());
        openDealer(new LogicConnectionDesignDealer());
        openDealer(new LogicFunctionDesignDealer());

        primaryStage.setTitle("palma");
        openStage(primaryStage).openScene("welcome").show();
    }
}

