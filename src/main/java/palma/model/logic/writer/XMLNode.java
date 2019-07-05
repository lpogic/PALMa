package palma.model.logic.writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Obiekt, ktory moze zostac zapisany w formacie xml
 */
public class XMLNode{
    private String name;
    private Map<String, Object> attributes;
    private List<XMLizable> children;

    public XMLNode(String name) {
        this.name = name;
        this.attributes = new HashMap<>();
        children = new ArrayList<>();
    }

    public void add(String attributeName, Object attributeValue){
        attributes.put(attributeName,attributeValue);
    }

    public List<XMLizable> getChildren() {
        return children;
    }

    public void appendTo(StringBuilder str, String prefix){
        str.append(prefix).append("<").append(name);
        for(String it : attributes.keySet()){
            str.append(" ").append(it).append("=\"")
                    .append(attributes.get(it)).append("\"");
        }
        if(children.isEmpty()){
            str.append("/>\n");
        } else {
            str.append(">\n");
            children.forEach(x -> x.toXmlNode().appendTo(str, prefix + "\t"));
            str.append(prefix).append("</").append(name).append(">\n");
        }
    }
}
