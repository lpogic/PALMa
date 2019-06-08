package palma.model.logic.writer;

public class EmptyXMLNode extends XMLNode {

    public EmptyXMLNode(String name) {
        super(name);
    }

    @Override
    public void appendTo(StringBuilder str, String prefix) {
    }
}
