package com.cordova.plugin.android.fingerprintauth;

import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;

public class AuthenticationResultAdapterV2 {
    private BiometricPrompt.AuthenticationResult biometricResult;
    private FingerprintManager.AuthenticationResult fingerprintResult;

    public AuthenticationResultAdapterV2(BiometricPrompt.AuthenticationResult biometricResult) {
        this.biometricResult = biometricResult;
    }

    public AuthenticationResultAdapterV2(FingerprintManager.AuthenticationResult fingerprintResult) {
        this.fingerprintResult = fingerprintResult;
    }

    CryptoObjectAdapter getCryptoObject() {
        return this.biometricResult == null
                ? new CryptoObjectAdapter(this.fingerprintResult)
                : new CryptoObjectAdapter(this.biometricResult);
    }
}
