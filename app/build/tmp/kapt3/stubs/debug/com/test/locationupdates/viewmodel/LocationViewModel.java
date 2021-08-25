package com.test.locationupdates.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000b0\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/test/locationupdates/viewmodel/LocationViewModel;", "Landroidx/lifecycle/ViewModel;", "repo", "Lcom/test/locationupdates/model/repository/LocationRepo;", "(Lcom/test/locationupdates/model/repository/LocationRepo;)V", "addLocation", "", "location", "Lcom/test/locationupdates/model/room/UserLocation;", "getLocation", "Landroidx/lifecycle/LiveData;", "", "app_debug"})
public final class LocationViewModel extends androidx.lifecycle.ViewModel {
    private final com.test.locationupdates.model.repository.LocationRepo repo = null;
    
    public LocationViewModel(@org.jetbrains.annotations.NotNull()
    com.test.locationupdates.model.repository.LocationRepo repo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.test.locationupdates.model.room.UserLocation>> getLocation() {
        return null;
    }
    
    public final void addLocation(@org.jetbrains.annotations.NotNull()
    com.test.locationupdates.model.room.UserLocation location) {
    }
}