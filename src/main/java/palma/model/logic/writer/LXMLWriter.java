package palma.model.logic.writer;

import palma.model.logic.builder.device.DeviceAdapter;
import palma.model.logic.builder.device.DeviceAdapterCase;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class LXMLWriter {
    public static String xmlSettings = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    private File file;
    private Set<String> imports;
    private StringBuilder nodeTree;

    public LXMLWriter(File file){
         this.file = file;
    }

    public LXMLWriter(String filePath){
        this.file = new File(filePath);
    }

    public static void write(DeviceAdapterCase devices, String filePath)throws FileNotFoundException{
        new LXMLWriter(filePath).write(devices);
    }

    public void write(DeviceAdapterCase devices) throws FileNotFoundException{
        PrintWriter out = new PrintWriter(new FileOutputStream(file));
        imports = new HashSet<>();
        nodeTree = new StringBuilder();

        addLine("<logic>",0);
        addLine("<devices>",1);
        for(DeviceAdapter it : devices.getBy(DeviceAdapter::isGraphical)){
            it.toXmlNode().appendTo(nodeTree,"\t\t");
        }
        addLine("</devices>",1);
        addLine("<functions>",1);
        for(DeviceAdapter it : devices.getBy(d-> !d.isGraphical())){
            it.toXmlNode().appendTo(nodeTree,"\t\t");
        }
        addLine("</functions>",1);
        addLine("</logic>",0);

        out.println(xmlSettings);
        out.println();
        for(String it : imports){
            out.println(it);
        }
        out.println();
        out.println(nodeTree.toString());
        System.out.println(nodeTree.toString());
        out.close();
    }

    private void addLine(String string, int tabs){
        for(;tabs > 0;--tabs)nodeTree.append("\t");
        nodeTree.append(string).append("\n");
    }
}
