package com.cordova.plugin.android.fingerprintauth;

import android.hardware.biometrics.BiometricPrompt;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;

public class AuthenticationResultAdapterV2 {
    private BiometricPrompt.AuthenticationResult biometricResult;
    private FingerprintManagerCompat.AuthenticationResult fingerprintResult;

    public AuthenticationResultAdapterV2(BiometricPrompt.AuthenticationResult biometricResult) {
        this.biometricResult = biometricResult;
    }

    public AuthenticationResultAdapterV2(FingerprintManagerCompat.AuthenticationResult fingerprintResult) {
        this.fingerprintResult = fingerprintResult;
    }

    CryptoObjectAdapter getCryptoObject() {
        return this.biometricResult == null
                ? new CryptoObjectAdapter(this.fingerprintResult)
                : new CryptoObjectAdapter(this.biometricResult);
    }
}
