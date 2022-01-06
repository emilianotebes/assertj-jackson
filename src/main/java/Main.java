import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonDiff;
import org.assertj.core.api.Assertions;

public class Main {

    public static void main(String[] args) {
        ObjectMapper om = new ObjectMapper();

        try {
            JsonNode objectNode0 = om.readTree("{\"someNotImportantValue\":1,\"importantValue\":\"10\"}");
            JsonNode objectNode1 = om.readTree("{\"someNotImportantValue\":15,\"importantValue\":\"1\"}");

            boolean equals = objectNode1.equals(objectNode1);
            System.out.println(equals);

            //This works, but does not enable to ignore any field
//            Assertions.assertThat(objectNode0).isEqualTo(objectNode1);

            //We would expect this sentence to fail, since importantValue is still different, but it does not.
            Assertions.assertThat(objectNode0).usingRecursiveComparison().ignoringFields("someNotImportantValue").isEqualTo(objectNode1);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
