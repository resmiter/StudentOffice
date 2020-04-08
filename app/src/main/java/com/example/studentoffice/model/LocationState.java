package com.example.studentoffice.model;

import android.location.Location;

public class LocationState {
    private Boolean loading;
    private Location location;

    public LocationState(Boolean loading, Location location) {
        this.loading = loading;
        this.location = location;
    }

    public Boolean getLoading() {
        return loading;
    }

    public void setLoading(Boolean loading) {
        this.loading = loading;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
