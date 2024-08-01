package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {

    private final String filePath;

    public FileKV(String filePath, Map<String, String> map) {
        this.filePath = filePath;
        Utils.writeFile(filePath, Utils.serialize(map));
    }

    @Override
    public void set(String key, String value) {
        var contentFile = Utils.readFile(this.filePath);
        var storage = Utils.deserialize(contentFile);
        storage.put(key, value);
        var newContentFile = Utils.serialize(storage);
        Utils.writeFile(this.filePath, newContentFile);
    }

    @Override
    public void unset(String key) {
        var contentFile = Utils.readFile(this.filePath);
        var storage = Utils.deserialize(contentFile);
        storage.remove(key);
        var newContentFile = Utils.serialize(storage);
        Utils.writeFile(this.filePath, newContentFile);
    }

    @Override
    public String get(String key, String defaultValue) {
        var contentFile = Utils.readFile(this.filePath);
        var storage = Utils.deserialize(contentFile);
        return (storage.get(key) == null) ? defaultValue : storage.get(key);
    }

    @Override
    public Map<String, String> toMap() {
        var contentFile = Utils.readFile(this.filePath);
        var storage = Utils.deserialize(contentFile);
        return new HashMap<>(storage);
    }
}
// END
