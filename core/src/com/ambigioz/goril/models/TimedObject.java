package com.ambigioz.goril.models;

import com.ambigioz.goril.models.objects.FallingObject;

public class TimedObject {

    FallingObject object;
    int time;

    public FallingObject getObject() {
        return object;
    }

    public int getTime() {
        return time;
    }

    public TimedObject(FallingObject object, int time) {

        this.object = object;
        this.time = time;
    }
}
