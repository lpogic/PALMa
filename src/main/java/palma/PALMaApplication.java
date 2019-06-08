package palma;

import javafx.stage.Stage;
import palma.core.OpenRoot;
import palma.dealer.LogicConnectionDesignDealer;
import palma.dealer.LogicDesignDealer;
import palma.dealer.LogicFunctionDesignDealer;
import palma.dealer.XMLDealer;

public class PALMaApplication extends OpenRoot {

    @Override
    public void employ(Stage primaryStage) {

        openDealer(new LogicDesignDealer());
        openDealer(new LogicConnectionDesignDealer());
        openDealer(new LogicFunctionDesignDealer());
        openDealer(new XMLDealer());

        primaryStage.setTitle("palma");
        openStage(primaryStage).openScene("welcome").show();
    }
}

