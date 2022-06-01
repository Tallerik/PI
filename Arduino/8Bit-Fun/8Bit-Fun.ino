/*
Not working, because C++

*/

int startpin = 2;
int endpin = 9;
int tasterA = 10;
int tasterB = 11;

int current_pattern = 0;
int maxpattern = 2;
int current_step = 0;

int loopruns = 0;

bool* lights = new bool[8];

/*
bool patterns[3][2][8] = {
    {
        {
            0,1,0,1,0,1,0,1
        },
        {
            1,0,1,0,1,0,1,0
        }
    },
    {},
    {}
};

*/


void setup() {
    Serial.begin(115200);
    Serial.println("Welcome from Serial");
    for (int i = startpin; i <= endpin; i++)
    {
        
        pinMode(i, OUTPUT);

    }
    for (int i = 0; i < 8; i++)
    {
        lights[i] = false;
    }
    
    pinMode(tasterA, INPUT);
    pinMode(tasterB, INPUT);
}

void loop() {
    
    if(loopruns > 10) {
        checktaster();
        loopruns = 0;
    }


    step();
    display();
    Serial.println(current_pattern);
    Serial.println(current_step);
    for (size_t i = 0; i < 8; i++)
    {
        Serial.println(lights[i]);
    }
    

    delay(50);
    loopruns++;
}


void step() {
    //lights = patterns[current_pattern][current_step];     
    current_step++;
    if(current_step == 2) current_step = 0;
}


void display() {
    int count = 0;
    for (int i = startpin; i <= endpin; i++)
    {
        digitalWrite(i, (lights[count]) ? HIGH : LOW);
        count++;
    }
}
void checktaster() {
    if(digitalRead(tasterA) == HIGH) {
        if(current_pattern > 0) {
            current_pattern--;
            current_step = 0;
        }
    } else if (digitalRead(tasterB) == HIGH) {
        if(current_pattern < maxpattern) {
            current_pattern++;
            current_step = 0;
        }   
    }
}