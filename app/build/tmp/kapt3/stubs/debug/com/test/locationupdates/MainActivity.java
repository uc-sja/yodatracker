package com.test.locationupdates;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\u001fB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u000fH\u0014J-\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00042\u000e\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016\u00a2\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\u000fH\u0014J\b\u0010\u001b\u001a\u00020\u000fH\u0002J\b\u0010\u001c\u001a\u00020\u000fH\u0002J\u0010\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/test/locationupdates/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "LOCATION_REQUEST_PERMISSION_CODE", "", "TAG", "", "binding", "Lcom/test/locationupdates/databinding/ActivityMainBinding;", "count", "locationViewModel", "Lcom/test/locationupdates/viewmodel/LocationViewModel;", "LocationPermissionsGranted", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "requestLocationPermissions", "startLocationTracking", "updateBtnState", "requestingLocation", "LocationBroadCastReceiver", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private final int LOCATION_REQUEST_PERMISSION_CODE = 2214;
    private final java.lang.String TAG = "MainActivity";
    private int count = 0;
    private com.test.locationupdates.viewmodel.LocationViewModel locationViewModel;
    private com.test.locationupdates.databinding.ActivityMainBinding binding;
    private java.util.HashMap _$_findViewCache;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void startLocationTracking() {
    }
    
    private final void requestLocationPermissions() {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    private final boolean LocationPermissionsGranted() {
        return false;
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    private final void updateBtnState(boolean requestingLocation) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016\u00a8\u0006\t"}, d2 = {"Lcom/test/locationupdates/MainActivity$LocationBroadCastReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/test/locationupdates/MainActivity;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_debug"})
    public final class LocationBroadCastReceiver extends android.content.BroadcastReceiver {
        
        public LocationBroadCastReceiver() {
            super();
        }
        
        @java.lang.Override()
        public void onReceive(@org.jetbrains.annotations.Nullable()
        android.content.Context context, @org.jetbrains.annotations.Nullable()
        android.content.Intent intent) {
        }
    }
}