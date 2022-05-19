void setup() {
    Serial.begin(9600);
    pinMode(13, OUTPUT);
}

void loop() {

    Serial.write("ON");
    digitalWrite(13, HIGH);
    delay(500);
    Serial.write("OFF");
    digitalWrite(13, LOW);
    delay(500);
}
