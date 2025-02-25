/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.cordova.plugin.android.fingerprintauth;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Small helper class to manage text/icon around fingerprint authentication UI.
 */
@TargetApi(23)
public class FingerprintUiHelperV2 extends FingerprintManager.AuthenticationCallback {

    static final long ERROR_TIMEOUT_MILLIS = 1600;
    static final long SUCCESS_DELAY_MILLIS = 1300;

    private final Context mContext;
    private final FingerprintManager mFingerprintManager;
    private final ImageView mIcon;
    private final TextView mErrorTextView;
    private final Callback mCallback;
    private CancellationSignal mCancellationSignal;
    private int mAttempts = 0;
    private static FingerprintManager.AuthenticationResult fingerprintResult;

    boolean mSelfCancelled;

    /**
     * Builder class for {@link FingerprintUiHelper} in which injected fields from Dagger
     * holds its fields and takes other arguments in the {@link #build} method.
     */
    public static class FingerprintUiHelperBuilder {
        private final FingerprintManager mFingerPrintManager;
        private final Context mContext;

        public FingerprintUiHelperBuilder(Context context, FingerprintManager fingerprintManager) {
            mFingerPrintManager = fingerprintManager;
            mContext = context;
        }

        public FingerprintUiHelperV2 build(ImageView icon, TextView errorTextView, Callback callback) {
            return new FingerprintUiHelperV2(mContext, mFingerPrintManager, icon, errorTextView,
                    callback);
        }
    }

    /**
     * Constructor for {@link FingerprintUiHelper}. This method is expected to be called from
     * only the {@link FingerprintUiHelperBuilder} class.
     */
    private FingerprintUiHelperV2(Context context, FingerprintManager fingerprintManager,
            ImageView icon, TextView errorTextView, Callback callback) {
        mFingerprintManager = fingerprintManager;
        mIcon = icon;
        mErrorTextView = errorTextView;
        mCallback = callback;
        mContext = context;
    }

    public boolean isFingerprintAuthAvailable() {
        return mFingerprintManager.isHardwareDetected()
                && mFingerprintManager.hasEnrolledFingerprints();
    }

    public void startListening(FingerprintManager.CryptoObject cryptoObject) {
        if (!isFingerprintAuthAvailable()) {
            return;
        }
        mCancellationSignal = new CancellationSignal();
        mSelfCancelled = false;
        mFingerprintManager
                .authenticate(cryptoObject, mCancellationSignal, 0 /* flags */, this, null);

        int icv2_fp_40px_id = mContext.getResources()
                .getIdentifier("icv2_fp_40px", "drawable", FingerprintAuthV2.packageName);
        mIcon.setImageResource(icv2_fp_40px_id);
    }

    public void stopListening() {
        if (mCancellationSignal != null) {
            mSelfCancelled = true;
            mCancellationSignal.cancel();
            mCancellationSignal = null;
        }
    }

    @Override
    public void onAuthenticationError(int errMsgId, final CharSequence errString) {
        // FINGERPRINT_ERROR_USER_CANCELED is thrown when the user clicks the "cancel" button on the
        // native fingerprint overlay (this overlay appears often on devices with fingerprint on-screen functionality)
        // Error code 1010 was discovered on a Google Pixel 6 where the Home button was pressed during the Fingerprint dialog.
        if (errMsgId == 1010 || errMsgId == FingerprintManager.FINGERPRINT_ERROR_USER_CANCELED || errMsgId == FingerprintManager.FINGERPRINT_ERROR_CANCELED) {
            mCallback.onCancelledByUser();
            return;
        }
        if (!mSelfCancelled) {
            showError(errString);
            mIcon.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mCallback.onError(errString);
                }
            }, ERROR_TIMEOUT_MILLIS);
        }
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        showError(helpString);
    }

    @Override
    public void onAuthenticationFailed() {
        mAttempts++;
        int fingerprint_not_recognized_id = mContext.getResources()
                .getIdentifier("fingerprint_not_recognizedV2", "string",
                        FingerprintAuthV2.packageName);
        int fingerprint_too_many_attempts_id = mContext.getResources()
                .getIdentifier("fingerprint_too_many_attemptsV2", "string",
                        FingerprintAuthV2.packageName);
        final String too_many_attempts_string = mIcon.getResources().getString(
                fingerprint_too_many_attempts_id);
        if (mAttempts > FingerprintAuthV2.mMaxAttempts) {
            showError(too_many_attempts_string);
            mIcon.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mCallback.onError(too_many_attempts_string);
                }
            }, ERROR_TIMEOUT_MILLIS);
        } else {
            showError(mIcon.getResources().getString(
                    fingerprint_not_recognized_id));
        }
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        fingerprintResult = result;
        mErrorTextView.removeCallbacks(mResetErrorTextRunnable);
        int icv2_fingerprint_success_id = mContext.getResources()
                .getIdentifier("icv2_fingerprint_success", "drawable", FingerprintAuthV2.packageName);
        mIcon.setImageResource(icv2_fingerprint_success_id);
        int success_color_id = mContext.getResources()
                .getIdentifier("success_colorV2", "color", FingerprintAuthV2.packageName);
        mErrorTextView.setTextColor(
                mErrorTextView.getResources().getColor(success_color_id, null));
        int fingerprint_success_id = mContext.getResources()
                .getIdentifier("fingerprint_successV2", "string", FingerprintAuthV2.packageName);
        mErrorTextView.setText(
                mErrorTextView.getResources().getString(fingerprint_success_id));
        mIcon.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCallback.onAuthenticated(fingerprintResult);
            }
        }, SUCCESS_DELAY_MILLIS);
    }

    private void showError(CharSequence error) {
        int icv2_fingerprint_error_id = mContext.getResources()
                .getIdentifier("icv2_fingerprint_error", "drawable", FingerprintAuthV2.packageName);
        mIcon.setImageResource(icv2_fingerprint_error_id);
        mErrorTextView.setText(error);
        int warning_color_id = mContext.getResources()
                .getIdentifier("warning_colorV2", "color", FingerprintAuthV2.packageName);
        mErrorTextView.setTextColor(
                mErrorTextView.getResources().getColor(warning_color_id, null));
        mErrorTextView.removeCallbacks(mResetErrorTextRunnable);
        mErrorTextView.postDelayed(mResetErrorTextRunnable, ERROR_TIMEOUT_MILLIS);
    }

    Runnable mResetErrorTextRunnable = new Runnable() {
        @Override
        public void run() {
            int hint_color_id = mContext.getResources()
                    .getIdentifier("hint_colorV2", "color", FingerprintAuthV2.packageName);
            mErrorTextView.setTextColor(
                    mErrorTextView.getResources().getColor(hint_color_id, null));
            int fingerprint_hint_id = mContext.getResources()
                    .getIdentifier("fingerprint_hintV2", "string", FingerprintAuthV2.packageName);
            mErrorTextView.setText(
                    mErrorTextView.getResources().getString(fingerprint_hint_id));
            int icv2_fp_40px_id = mContext.getResources()
                    .getIdentifier("icv2_fp_40px", "drawable", FingerprintAuthV2.packageName);
            mIcon.setImageResource(icv2_fp_40px_id);
        }
    };

    public interface Callback {

        void onAuthenticated(FingerprintManager.AuthenticationResult result);

        void onError(CharSequence errString);

        void onCancelledByUser();
    }
}
