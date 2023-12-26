package ans.pojo;

import ans.pojo.AlertMessage;
import ans.pojo.GeneralInfo;
import ans.pojo.StatusLocalis;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class AlertMessageDeserializer extends JsonDeserializer<AlertMessage> {
    @Override
    public AlertMessage deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        AlertMessage alertMessage = new AlertMessage();

        GeneralInfo generalInfo = jp.getCodec().treeToValue(node.get("generalInfo"), GeneralInfo.class);
        StatusLocalis statusLocalis = jp.getCodec().treeToValue(node.get("statusLocalis"), StatusLocalis.class);

        alertMessage.setGeneralInfo(generalInfo);
        alertMessage.setStatusLocalis(statusLocalis);

        if (generalInfo != null) {
            alertMessage.setPatientId(generalInfo.getPatient_id());
            alertMessage.setAge(generalInfo.getAge());
            alertMessage.setGender(generalInfo.getGender());
            alertMessage.setDiagnosis(generalInfo.getDiagnosis());
            alertMessage.setMedication(generalInfo.getMedication());
            alertMessage.setInsuranceType(generalInfo.getInsurance_type());
            alertMessage.setTreatmentDuration(generalInfo.getTreatment_duration());
            alertMessage.setEmail(generalInfo.getEmail());
        }
        if (statusLocalis != null) {
            alertMessage.setDate(statusLocalis.getDate());
            alertMessage.setHeartRate(statusLocalis.getHeart_rate());
            alertMessage.setRespiratoryRate(statusLocalis.getRespiratory_rate());
            alertMessage.setSpO2(statusLocalis.getSpO2());
            alertMessage.setTemperature(statusLocalis.getTemperature());
            alertMessage.setStatus(statusLocalis.getStatus());
        }

        return alertMessage;
    }
}
