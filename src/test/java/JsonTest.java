import com.fasterxml.jackson.databind.ObjectMapper;
import json.JsonDescription;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonTest {
    ClassLoader cl = JsonTest.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Порверка полей Json файла")
    void checkJsonAnswerTest() throws Exception {

        try (
                InputStream resource = cl.getResourceAsStream("json.json")
        ) {
            assert resource != null;
            try (InputStreamReader reader = new InputStreamReader(resource)
            ) {
                JsonDescription json = objectMapper.readValue(reader, JsonDescription.class);
                assertThat(json.getName()).isEqualTo("Marina");
                assertThat(json.getAge()).isEqualTo("34");
                assertThat(json.getProfession()).isEqualTo("Qa");
                assertThat(json.getSkills()).contains("Java",
                        "Automation");

            }
        }
    }
}