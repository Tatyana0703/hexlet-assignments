package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {

    private final String body;
    private final List<Tag> children;

    public PairedTag(String tagName, Map<String, String> tagAttributes, String body, List<Tag> children) {
        super(tagName, tagAttributes);
        this.body = body;
        this.children = children;
    }

    @Override
    public String toString() {
        String childrenValues = children.stream().map(Tag::toString).collect(Collectors.joining());
        return String.format("<%s%s>%s%s</%s>", this.tagName, this.getAttributeValues(), this.body,
                childrenValues, this.tagName);
    }
}
// END
