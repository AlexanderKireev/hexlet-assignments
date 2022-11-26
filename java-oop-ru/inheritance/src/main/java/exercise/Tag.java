package exercise;

import java.util.Map;

// BEGIN
public class Tag {
    protected String name;
    private final Map<String, String> attr;

    public Tag(String name, Map<String, String> attr) {
        this.name = name;
        this.attr = attr;
    }

    public String toString() {
        var str = new StringBuilder("<");
        str.append(name).append(" ");
        for (Map.Entry<String, String> m : attr.entrySet()) {
            str.append(m.getKey()).append("=\"");
            str.append(m.getValue()).append("\" ");
        }
        str.delete(str.length() - 1, str.length()).append(">");
    return str.toString();
    }

}
// END
