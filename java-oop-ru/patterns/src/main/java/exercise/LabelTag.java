package exercise;

// BEGIN
public class LabelTag implements TagInterface {

    private final String value;
    private final TagInterface innerTag;

    public LabelTag(String value, TagInterface innerTag) {
        this.value = value;
        this.innerTag = innerTag;
    }

    @Override
    public String render() {
        return "<label>" + this.value + innerTag.render() + "</label>";
    }
}
// END
