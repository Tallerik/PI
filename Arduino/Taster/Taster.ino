int tasterpin = 8;
int rot = 12;
int grun = 7;
void setup() {
    pinMode(tasterpin, INPUT);
    pinMode(rot, OUTPUT);
    pinMode(grun, OUTPUT);
}

void loop() {
    if(digitalRead(tasterpin) == LOW) {
        digitalWrite(rot, HIGH);
        digitalWrite(grun, LOW);
    } else {
        digitalWrite(rot, LOW);
        digitalWrite(grun, HIGH);
    }
}