package palma.model.logic.loader;

import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Circle;
import palma.model.logic.devices.Device;
import palma.model.logic.devices.event.EventButtonDevice;
import palma.model.logic.devices.event.EventDevice;
import palma.model.logic.devices.event.EventFileDevice;
import palma.model.logic.devices.regular.ButtonDevice;
import palma.model.logic.devices.regular.FileDevice;
import palma.model.logic.devices.regular.LampDevice;
import palma.model.logic.devices.regular.RegularDevice;
import palma.model.logic.loader.exceptions.LXMLParsingException;

import javax.xml.namespace.QName;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;

/**
 * Fabryka urzadzen. Tworzy urzadzenia na bazie elementow odczytanych z xml
 */
public class DeviceFactory {
    private Scene scene;

    public DeviceFactory() {
    }

    public DeviceFactory(Scene scene) {
        this.scene = scene;
    }

    public Device makeDevice(StartElement element, SignalParametersCollection signals)throws LXMLParsingException{
        if(LXMLUtils.attributeEquals(element,"control","event")){
            signals.forEach(e->e.setDeviceDriven(true));
            return makeEventDevice(element,signals);
        } else {
            return makeRegularDevice(element,signals);
        }
    }

    public EventDevice makeEventDevice(StartElement element, SignalParametersCollection signals)throws LXMLParsingException{
        if(LXMLUtils.attributeEquals(element,"type","file")){
            return makeEventFileDevice(element,signals);
        } else {
            switch(element.getAttributeByName(QName.valueOf("class")).getValue().toLowerCase()){
                case "button":
                    return makeEventButtonDevice(element,signals);
                default:
                    throw new LXMLParsingException("Unrecognized event controlled device");
            }
        }
    }

    public RegularDevice makeRegularDevice(StartElement element, SignalParametersCollection signals)throws LXMLParsingException{
        if(LXMLUtils.attributeEquals(element,"type","file")){
            return makeFileDevice(element,signals);
        } else {
            switch(element.getAttributeByName(QName.valueOf("class")).getValue().toLowerCase()){
                case "button":
                    return makeButtonDevice(element,signals);
                case "lamp":
                    return makeLampDevice(element,signals);
                default:
                    throw new LXMLParsingException("Unrecognized regular device");
            }
        }
    }

    public EventFileDevice makeEventFileDevice(StartElement element, SignalParametersCollection signals){
        return new EventFileDevice();
    }

    public FileDevice makeFileDevice(StartElement element, SignalParametersCollection signals){
        return new FileDevice();
    }

    public EventButtonDevice makeEventButtonDevice(StartElement element, SignalParametersCollection signals){
        return new EventButtonDevice();
    }

    public ButtonDevice makeButtonDevice(StartElement element, SignalParametersCollection signals)throws LXMLParsingException{
        SignalParametersCollection outputs = signals.getOutputs();
        if(outputs.size() != 1)throw new LXMLParsingException("Button need to has exactly one output");

        Attribute idAttribute = element.getAttributeByName(QName.valueOf("id"));
        if(idAttribute == null)throw new LXMLParsingException("Button need to has an id attribute");
        String id = idAttribute.getValue();
        ToggleButton button = (ToggleButton) scene.lookup("#" + id);
        if(button == null)throw new LXMLParsingException("Button '" + id + "' doesnt found on the scene");
        return new ButtonDevice(outputs.getFirst().getSignal(),button.selectedProperty());
    }

    public LampDevice makeLampDevice(StartElement element, SignalParametersCollection signals)throws LXMLParsingException{
        SignalParametersCollection inputs = signals.getInputs();
        if(inputs.size() != 1)throw new LXMLParsingException("Lamp need to has exactly one input");

        Attribute idAttribute = element.getAttributeByName(QName.valueOf("id"));
        if(idAttribute == null)throw new LXMLParsingException("Lamp need to has an id attribute");
        String id = idAttribute.getValue();
        Circle circle = (Circle) scene.lookup("#" + id);
        if(circle == null)throw new LXMLParsingException("Lamp '" + id + "' doesnt found on the scene");
        return new LampDevice(inputs.getFirst().getSignal(),circle.fillProperty());
    }
}
