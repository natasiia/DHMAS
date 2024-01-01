package pdcs;

public class HealthData {
        private Integer patient_id;
        private Integer heart_rate;
        private Integer SpO2;
        private Integer respiratory_rate;
        private Double temperature;

        // Getters and setters for the health data
        public int getHeart_rate() {
            return heart_rate;
        }

        public void setHeart_rate(int heart_rate) {
            this.heart_rate = heart_rate;
        }

        public int getSpO2() {
            return SpO2;
        }

        public void setSpO2(int SpO2) {
            this.SpO2 = SpO2;
        }

        public int getRespiratory_rate() {
            return respiratory_rate;
        }

        public void setRespiratory_rate(int respiratory_rate) {
            this.respiratory_rate = respiratory_rate;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public int getPatient_id() {
            return patient_id;
        }

        public void setPatient_id(int patient_id) {
            this.patient_id = patient_id;
        }

        // Check if all data fields are set
        public boolean isComplete() {
            return heart_rate != null && SpO2 != null && respiratory_rate != null && temperature != null;
        }

        // Method to return a string representation of the health data
        @Override
        public String toString() {
            return "HealthData{" +
                    "patient_id=" + patient_id +
                    ", heart_rate=" + heart_rate +
                    ", SpO2=" + SpO2 +
                    ", respiratory_rate=" + respiratory_rate +
                    ", temperature=" + temperature +
                    '}';
        }
    }
