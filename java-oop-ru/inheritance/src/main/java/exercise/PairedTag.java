package exercise;

import java.util.Map;
import java.util.List;
//import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private final String body;
    private final List<Tag> children;

    public PairedTag(String name, Map<String, String> attr, String body, List<Tag> children) {
        super(name, attr);
        this.body = body;
        this.children = children;
    }

    @Override
    public String toString() {
        var str = new StringBuilder(super.toString());
        str.append(body);

        for (Tag child : children) str.append(child.toString());

        // или по старинке
//        if (children.size() > 0) {
//            for (Tag child : children) {
//                str.append(child.toString());
//            }
//        }

        str.append("</").append(name).append(">");
        return str.toString();
    }
}
// END
