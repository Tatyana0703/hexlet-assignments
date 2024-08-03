package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {

    protected final String tagName;
    protected final Map<String, String> tagAttributes;

    public Tag(String tagName, Map<String, String> tagAttributes) {
        this.tagName = tagName;
        this.tagAttributes = tagAttributes;
    }

    protected String getAttributeValues() {
        String result = this.tagAttributes.entrySet().stream()
                .map(e -> String.format("%s=\"%s\"", e.getKey(), e.getValue())).collect(Collectors.joining(" ")).trim();
        return (result.isBlank()) ? result : " " + result;
    }

    public String toString() {
        return String.format("<%s%s>", this.tagName, this.getAttributeValues());
    }
}
// END
