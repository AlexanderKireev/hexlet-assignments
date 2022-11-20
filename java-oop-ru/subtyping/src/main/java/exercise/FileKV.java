package exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private final String path;
    public FileKV(String path, Map<String, String> storage) throws IOException {
        Path filepath = Paths.get(path).toAbsolutePath().normalize();
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<>(storage));
        Files.writeString(filepath, content, StandardOpenOption.CREATE);

        this.path = path;
    }
    @Override
    public void set(String key, String value) {
        Map<String, String> mmm = new HashMap<>(Utils.unserialize(Utils.readFile(path)));
        mmm.put(key, value);
        Utils.writeFile(path, Utils.serialize(mmm));
    }

    @Override
    public void unset(String key) {
        Map<String, String> mmm = new HashMap<>(Utils.unserialize(Utils.readFile(path)));
        mmm.remove(key);
        Utils.writeFile(path, Utils.serialize(mmm));

    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> mmm = new HashMap<>(Utils.unserialize(Utils.readFile(path)));
        return mmm.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> mmm = new HashMap<>(Utils.unserialize(Utils.readFile(path)));
        return new HashMap<>(mmm);
    }
}
// END
