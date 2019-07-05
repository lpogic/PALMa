package palma.core.pane;

/**
 * Kontroler widoku. Tworzony przy wczytywaniu pliku fxml
 */
public abstract class OpenController extends OpenPaneScion {

    protected OpenController(){
        super(null);
    }

    /**
     * Metoda inicjalizacyjna. Wywolywana automatycznie, zaraz po utworzeniu obiektu. Podczas wywolania
     * obiekt ma juz ustalony rodowod i moze sie odwolywac do rodzicow (oraz obiektu shop)
     */
    protected abstract void employ();

    /**
     * Metoda wywolywana przed kazdym wyswietleniem lub przekazaniem widoku
     */
    protected abstract void dress();
}
