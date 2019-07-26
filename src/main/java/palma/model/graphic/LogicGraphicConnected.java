package palma.model.graphic;

import javafx.util.Pair;
import palma.model.logic.builder.device.DeviceAdapter;

import java.util.*;

public class LogicGraphicConnected {
   public static List<Pair<SelectableNode, DeviceAdapter>> listDeviceConnected = new ArrayList<Pair<SelectableNode, DeviceAdapter>>();

       public static Pair<SelectableNode, DeviceAdapter> getObjectWithKey(SelectableNode key)
       {
           Pair<SelectableNode, DeviceAdapter> ans = null;
           for (Pair <SelectableNode,DeviceAdapter> temp : listDeviceConnected)
           {
              if(key == temp.getKey() )
                  ans = temp;
           }
           return ans;
       }
       public static Pair<SelectableNode, DeviceAdapter> getObjectWithValue(DeviceAdapter value)
       {
           Pair<SelectableNode, DeviceAdapter> ans = null;
           for (Pair <SelectableNode,DeviceAdapter> temp : listDeviceConnected)
           {
               if(value == temp.getValue() )
                   ans = temp;
           }
           return ans;
       }

       public static void changeNameKeyWithValue(DeviceAdapter value) {
           int index =0;
           for (Pair<SelectableNode, DeviceAdapter> temp : listDeviceConnected) {
               if (value == temp.getValue()) {
                   if (temp.getKey() instanceof MyButton)
                   {
                       ((MyButton) listDeviceConnected.get(index).getKey()).defaultName = value.getName();
                   }
                   else
                       ((MyCircle) listDeviceConnected.get(index).getKey()).defaultName = value.getName();
               }
               index++;
           }
       }

       public static void changeNameValueWithKey(SelectableNode key) {
           int index =0;
           for (Pair<SelectableNode, DeviceAdapter> temp : listDeviceConnected) {
               if (key == temp.getKey()) {
                   if (key instanceof MyButton)
                       listDeviceConnected.get(index).getValue().setName(((MyButton) key).defaultName);
                   else
                       listDeviceConnected.get(index).getValue().setName(((MyCircle) key).defaultName);
               }
               index++;
           }
       }
}
