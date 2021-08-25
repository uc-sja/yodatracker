package com.test.locationupdates;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 02\u00020\u0001:\u00010B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0004H\u0002J\u0014\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020\u001eH\u0016J\b\u0010\'\u001a\u00020\u001eH\u0016J\"\u0010(\u001a\u00020\u00062\b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006H\u0016J\b\u0010+\u001a\u00020\u001eH\u0002J\b\u0010,\u001a\u00020\u001eH\u0002J\u0010\u0010-\u001a\u00020\u001e2\u0006\u0010.\u001a\u00020/H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lcom/test/locationupdates/LocationService;", "Landroid/app/Service;", "()V", "NOTIFICATION_CHANNEL_ID", "", "NOTIFICATION_ID", "", "TAG", "fusedLocationClient1", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "fusedLocationClient2", "job", "Lkotlinx/coroutines/Job;", "locationCallback1", "Lcom/google/android/gms/location/LocationCallback;", "locationCallback2", "locationDao", "Lcom/test/locationupdates/model/room/UserLocationDao;", "locationRepo", "Lcom/test/locationupdates/model/repository/LocationRepo;", "locationRequest1", "Lcom/google/android/gms/location/LocationRequest;", "locationRequest2", "locationViewModel", "Lcom/test/locationupdates/viewmodel/LocationViewModel;", "notificationManager", "Landroid/app/NotificationManager;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "createLocationRequest", "", "createNotification", "Landroid/app/Notification;", "notificationText", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "flags", "startId", "startLocationUpdates", "stopLocationUpdates", "updateLocation", "location", "Landroid/location/Location;", "Companion", "app_debug"})
public final class LocationService extends android.app.Service {
    private com.test.locationupdates.model.repository.LocationRepo locationRepo;
    private com.test.locationupdates.model.room.UserLocationDao locationDao;
    private com.test.locationupdates.viewmodel.LocationViewModel locationViewModel;
    private final int NOTIFICATION_ID = 123456;
    private android.app.NotificationManager notificationManager;
    private final java.lang.String NOTIFICATION_CHANNEL_ID = "10332";
    private final java.lang.String TAG = "LocationService";
    private com.google.android.gms.location.LocationRequest locationRequest1;
    private com.google.android.gms.location.LocationRequest locationRequest2;
    private com.google.android.gms.location.LocationCallback locationCallback1;
    private com.google.android.gms.location.LocationCallback locationCallback2;
    private com.google.android.gms.location.FusedLocationProviderClient fusedLocationClient1;
    private com.google.android.gms.location.FusedLocationProviderClient fusedLocationClient2;
    private kotlinx.coroutines.Job job;
    private kotlinx.coroutines.CoroutineScope scope;
    @org.jetbrains.annotations.NotNull()
    public static final com.test.locationupdates.LocationService.Companion Companion = null;
    private static boolean requestingLocation = false;
    
    public LocationService() {
        super();
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    private final void updateLocation(android.location.Location location) {
    }
    
    private final void createLocationRequest() {
    }
    
    private final void stopLocationUpdates() {
    }
    
    private final void startLocationUpdates() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    private final android.app.Notification createNotification(java.lang.String notificationText) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/test/locationupdates/LocationService$Companion;", "", "()V", "requestingLocation", "", "getRequestingLocation", "()Z", "setRequestingLocation", "(Z)V", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final boolean getRequestingLocation() {
            return false;
        }
        
        public final void setRequestingLocation(boolean p0) {
        }
    }
}