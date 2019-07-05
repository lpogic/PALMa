package palma.model.logic.loader;

import palma.model.logic.LogicModel;
import palma.model.logic.devices.regular.RegularDevice;
import palma.model.logic.functions.Function;
import palma.model.logic.devices.Device;
import palma.model.logic.loader.exceptions.LXMLParsingException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.*;
import java.net.URL;
import java.util.*;

/**
 * Loader plikow lxml (xml). Sluzy do zaladowania z pliku xml projektu logiki
 */
public class LXMLLoader {
    private URL url;
    private DeviceFactory deviceFactory;

    public LXMLLoader(URL url) {
        this.url = url;
    }

    public void setDeviceFactory(DeviceFactory deviceFactory) {
        this.deviceFactory = deviceFactory;
    }

    public static LogicModel load(URL url)throws Exception{
        return new LXMLLoader(url).load();
    }

    public LogicModel load() throws Exception {

        SignalParametersCollection localSignals = new SignalParametersCollection();
        SignalParametersCollection globalSignals = new SignalParametersCollection();
        List<Device> devices = new ArrayList<>();
        List<Function> functions = new ArrayList<>();

        Stack<StartElement> elements = new Stack<>();

        SignalParametersFactory signalParametersFactory = new SignalParametersFactory();
        if(deviceFactory == null)deviceFactory = new DeviceFactory();
        FunctionFactory functionFactory = new FunctionFactory();


        XMLEventReader eventReader = XMLInputFactory.newInstance().
                createXMLEventReader(url.openStream());

        while(eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();

            switch (event.getEventType()) {

                case XMLStreamConstants.START_ELEMENT:
                    elements.push(event.asStartElement());
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    StartElement element = elements.pop();
                    try {
                        switch (element.getName().getLocalPart().toLowerCase()) {
                            case "input":
                            case "output":
                            case "signal":
                                SignalParameters signal = signalParametersFactory.makeSignalParameters(element);
                                signal.weave(globalSignals.getFirstById(signal.getId()));
                                localSignals.add(signal);
                                globalSignals.add(signal);
                                break;
                            case "device":
                                devices.add(deviceFactory.makeDevice(element, localSignals));
                                localSignals = new SignalParametersCollection();
                                break;
                            case "function":
                                functions.add(functionFactory.makeFunction(element, localSignals));
                                localSignals = new SignalParametersCollection();
                        }
                    }catch(LXMLParsingException lxmle){
                        lxmle.addSuppressed(new LXMLParsingException("Malformed LXML at line " + element.getLocation().getLineNumber()));
                        throw lxmle;
                    }
                    break;
            }
        }

        List<RegularDevice> mornings = new ArrayList<>();
        List<RegularDevice> evenings = new ArrayList<>();

        for(Device it : devices){
            if(it instanceof RegularDevice){
                RegularDevice regularDevice = (RegularDevice)it;
                if(regularDevice.isMorning())mornings.add(regularDevice);
                if(regularDevice.isEvening())evenings.add(regularDevice);
            }
        }

        return new LogicModel(mornings,evenings,globalSignals.getNotDeviceDriven().mapSignal(),functions);
    }


}
