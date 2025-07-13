import java.security.KeyStore.Entry;
import java.util.Map;
import java.util.Objects;

public class utility {
    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (java.util.Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
