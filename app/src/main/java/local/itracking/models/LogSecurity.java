package local.itracking.models;

import java.util.Date;

public class LogSecurity
{
        Long DateTime;
        String Type;
        String Value;

        public LogSecurity(Long dateTime, String type, String value) {
                DateTime = dateTime;
                Type = type;
                Value = value;
        }

        public Long getDateTime() {
                return DateTime;
        }

        public void setDateTime(Long dateTime) {
                DateTime = dateTime;
        }

        public String getType() {
                return Type;
        }

        public void setType(String type) {
                Type = type;
        }

        public String getValue() {
                return Value;
        }

        public void setValue(String value) {
                Value = value;
        }
}
