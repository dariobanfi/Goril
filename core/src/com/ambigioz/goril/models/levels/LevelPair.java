package com.ambigioz.goril.models.levels;


import com.ambigioz.goril.models.objects.FallingObject;

public class LevelPair {
    private FallingObject object;
    private double spawnAtTime;

    public LevelPair(FallingObject object, double spawnAtTime) {
        this.object = object;
        this.spawnAtTime = spawnAtTime;
    }

    public FallingObject getObject() {
        return object;
    }

    public void setObject(FallingObject object) {
        this.object = object;
    }

    public double getSpawnAtTime() {
        return spawnAtTime;
    }

    public void setSpawnAtTime(float spawnAtTime) {
        this.spawnAtTime = spawnAtTime;
    }
}
