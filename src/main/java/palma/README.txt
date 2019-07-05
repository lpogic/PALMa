Tworzenie interfejsu użytkownika:

1. Tworzenie widoku przez wybór elementów, ustawienie wygladu i pozycji.

Stworzony widok zapisywany jest w formacie fxml, który jest domyślnym
formatem plików widoków JavaFX. Elementy widoku łączone są z programem
przy pomocy parametru id.


2. Tworzenie programu przez wybór elementów, ustawienie połączeń i parametrów.

Każdy element programu składa się ze zbioru wejść, wyjść i parametrów.
Wśród elementów programu wyróżniono urządzenia i funkcje.
Urządzenia pośredniczą pomiędzy programem, a użytkownikiem, natomiast funkcje przetwarzają
sygnały w ramach programu. Urządzenia zwykle posiadają parametr id, który odpowiada
atrybutowi id elementu z widoku.

Parametry mogą być ograniczane przez walidatory, które są sprawdzane podczas procesu
kompilacji. Podczas kompilacji sprawdzana jest również poprawność połączeń elementów.
Przyjęto zasadę, że każde wejście musi być podłączone do dokładnie jednego wyjścia,
a wyjścia mogą być podłączone do dowolnej liczby wejść, w szczególności mogą być niepodłączone.

Jeśli kompilacja zakończy się sukcesem, generowany jest plik xml.
W nim w dwóch grupach zapisane są osobno urządzenia i funkcje wraz z automatycznie ponumerowanymi
wejściami/wyjściami.


Wgrywanie programu:

Przed uruchomieniem pierwszego programu na urządzeniu docelowym, należy na nim zainstalować środowisko
uruchomieniowe - palma_colony (instalacja polega na skopiowaniu plików skompilowanego programu).
Pliki źródłowe stworzonej aplikacji to: graficzny *.fxml i logiki - *.lxml. Oba pliki
muszą się znaleźć w katalogu app/bin programu palma_colony. Następnie uruchomienie app/bin/launch
spowoduje uruchomienie aplikacji.


Działanie symulatora:

Parser plików xml odczytuje projekt logiki, tworzy odpowiednie klasy urządzeń i łączy wejścia z wyjściami.
Na tym etapie połączenie wejścia z wyjściem jest zamieniane na obiekt Signal, zmieniany przez jedno z urządzeń i
odczytywany przez drugie. Gdy parsowanie przebiegnie pomyślnie uruchamiana jest ze stałą częstotliwością pętla
symulacji (metoda run w LogicModel). Wykonuje ona kilka zadań: zresetowanie sygnałów wewnętrznych, odczytanie wejść,
symulacja i zapisanie wyjść. Przy odczytaniu wejść i zapisaniu wyjść mogą być przeprowadzane dodatkowe operacje jak
pomiar czasu lub detekcja zbocza.


Dodawanie nowego urządzenia (kreator interfejsów):

Załóżmy, że chcemy dodać nową funkcjonalność do naszego programu: sterowanie wyjściami cyfrowymi.
Na początek potrzebujemy stworzyć nową klasę urządzenia w model/logic/builder/device, dziedziczącą po klasie
DeviceAdapter. Przykładowe urządzenie będzie miało 1 wejście "set", którego stan będzie przepisywany na wyjście fizyczne,
oraz parametr nazwany "Numer wyjścia". Klasa urządzenia zostanie nazwana "ExternalOutputDevice".
Konstruktor urządzenia może wyglądać tak:

public ExternalOutputDevice() {
    getInputs().add(new Input(this, "set"));
    getParameters().add(new Parameter(this, new BoundedIntegerValidator(16,1),"Numer wyjścia", ""));
}

numery wyjść zostały ograniczone do liczb od 1 do 16. Po stworzeniu konstruktora pozostaje jeszcze zaimplementować 3 metody:
getDefaultName() - domyślna nazwa urządzenia. Zwykle jest to statyczny String
isGraphical() - czy urządzenie ma postać graficzną np. przycisk. Jeśli nie to metoda powinna zwracać false
toXmlNode() - transformacja do XmlNode. Node powinien być wygenerowany przez klasę DeviceAdapter,
a następnie rozszerzony o dodatkowe atrybuty. Dla ExternalOutputDevice są to typ, klasa i parametr. Na przykład:

public XMLNode toXmlNode() {
    XMLNode node = super.toXmlNode();
    node.add("type","file");
    node.add("class","digitalOutput");
    node.add("outputNumber",getParameters().getFirstByName("Numer wyjścia").getValue());
    return node;
}

Następnie w metodzie getDeviceButtons() klasy dealer/LogicDevicePickerDealer należy dodać nowe urządzenie. Można to zrobić
analogicznie jak dla już istniejących urządzeń, podmieniając nazwę klasy i zmiennych:

Button externalOutputButton = new Button(ExternalOutputDevice.defaultName);
externalOutputButton.setOnAction(e->{
    ExternalOutputDevice externalOutputDevice = new ExternalOutputDevice();
    externalOutputDevice.setName(IdProvider.getFreeDeviceName(shop().deal(LogicDesignDealer.getDevices),ExternalOutputDevice.defaultName));
    shop().deliver(LogicDesignDealer.selectedDevice, externalOutputDevice);
    shop().deal(LogicDesignDealer.addSelected);
});
buttons.add(externalOutputButton);

Po wykonaniu tych kroków w naszym programie można tworzyć aplikacje z funkcją sterowania wyjściami fizycznymi.


Dodawanie nowego urządzenia (symulator):

Projekt palma_colony należy rozszerzyć o klasę nowego urządzenia w logic/devices/regular i stworzyć parser urządzenia w
logic/loader/DeviceFactory lub logic/loader/FunctionFactory, w zależności od rodzaju urządzenia. Klasa nowego urządzenia
powinna dziedziczyć po klasie Device. Dla przykładowego urządzenia do sterowania wyjściami cyfrowymi można skorzystać
z istniejącej klasy ShellCmdDevice lub FieldToFileDevice.

W ogólnym przypadku urządzenie posiada kilka pól Signal oraz implementuje metody execute(), dawn() oraz dusk(). Metoda
dawn() wywoływana jest bezpośrednio przed symulacją i powinno w niej nastąpić zapisanie stanu urządzenia.
Metoda execute() powinna zwrócić false, jeśli z powodu niezdefiniowanych sygnałów wejściowych nie można przeprowadzić symulacji,
w przeciwnym przypadku przeprowadzić symulację i zwrócić true. Metoda dusk() jest wywoływana zaraz po symulacji i
jest wykorzystywana przez niektóre urządzenia.

Synały są tworzone przez fabrykę i przekazywane do konstruktora urządzenia.
Funkcja fabryki tworząca urządzenie może zostać zidentyfikowana na podstawie atrybutów elementu xml reprezentującego
urządzenie. Zwykle w tym celu są używane atrybuty type i class. Funkcję fabrykującą zaleca się wyposażyć w walidatory,
które pozwolą szybko wyłapać błędy składni i zmniejszą ryzyko powstawania błędów podczas symulacji.




W projekcie wykorzystano nowy wzorzec projektowy. Bazuje on na wzorcu MVC, jednak między warstwą Controller i Model
dodana została nowa warstwa - Shop. Controller nie ma bezpośredniego dostępu do Model, natomiast przy pomocy
obiektów Contract komunikuje się z Shop. Poprzez Shop odnajduje drugą stronę, która wcześniej złożyła ofertę w ramach
tego samego Contract i w zależności od rodzaju Contract, realizuje zgłoszoną ofertę na rzecz klienta.

W ten sposób rozluźnione zostają powiązania logiki widoku z logiką modelu, upraszczając rozbudowę, modyfikacje i testowanie
aplikacji.


Wykryte błędy:

Mimo, że program projektowy (palma) pozwala tworzyć układy sekwencyjne,
program uruchomieniowy (palma_colony) w aktualnej postaci potrafi zasymulować tylko układy kombinacyjne.
