var exec = require("cordova/exec");

function FingerprintAuthV2() {
    FingerprintAuthV2.prototype.ERRORS = {
        BAD_PADDING_EXCEPTION: "BAD_PADDING_EXCEPTION",
        CERTIFICATE_EXCEPTION: "CERTIFICATE_EXCEPTION",
        FINGERPRINT_CANCELLED: "FINGERPRINT_CANCELLED",
        FINGERPRINT_DATA_NOT_DELETED: "FINGERPRINT_DATA_NOT_DELETED",
        FINGERPRINT_ERROR: "FINGERPRINT_ERROR",
        FINGERPRINT_NOT_AVAILABLE: "FINGERPRINT_NOT_AVAILABLE",
        FINGERPRINT_PERMISSION_DENIED: "FINGERPRINT_PERMISSION_DENIED",
        FINGERPRINT_PERMISSION_DENIED_SHOW_REQUEST: "FINGERPRINT_PERMISSION_DENIED_SHOW_REQUEST",
        ILLEGAL_BLOCK_SIZE_EXCEPTION: "ILLEGAL_BLOCK_SIZE_EXCEPTION",
        INIT_CIPHER_FAILED: "INIT_CIPHER_FAILED",
        INVALID_ALGORITHM_PARAMETER_EXCEPTION: "INVALID_ALGORITHM_PARAMETER_EXCEPTION",
        IO_EXCEPTION: "IO_EXCEPTION",
        JSON_EXCEPTION: "JSON_EXCEPTION",
        MINIMUM_SDK: "MINIMUM_SDK",
        MISSING_ACTION_PARAMETERS: "MISSING_ACTION_PARAMETERS",
        MISSING_PARAMETERS: "MISSING_PARAMETERS",
        NO_SUCH_ALGORITHM_EXCEPTION: "NO_SUCH_ALGORITHM_EXCEPTION",
        SECURITY_EXCEPTION: "SECURITY_EXCEPTION"
    }
}

FingerprintAuthV2.prototype.encrypt = function (params, successCallback, errorCallback) {
    cordova.exec(
        successCallback,
        errorCallback,
        "FingerprintAuthV2",  // Java Class
        "encrypt", // action
        [ // Array of arguments to pass to the Java class
            params
        ]
    );
};

FingerprintAuthV2.prototype.decrypt = function (params, successCallback, errorCallback) {
    cordova.exec(
        successCallback,
        errorCallback,
        "FingerprintAuthV2",  // Java Class
        "decrypt", // action
        [ // Array of arguments to pass to the Java class
            params
        ]
    );
};

FingerprintAuthV2.prototype.delete = function (params, successCallback, errorCallback) {
    cordova.exec(
        successCallback,
        errorCallback,
        "FingerprintAuthV2",  // Java Class
        "delete", // action
        [ // Array of arguments to pass to the Java class
            params
        ]
    );
};

FingerprintAuthV2.prototype.dismiss = function (successCallback, errorCallback) {
    cordova.exec(
        successCallback,
        errorCallback,
        "FingerprintAuthV2",  // Java Class
        "dismiss", // action
        [{}]
    );
};

FingerprintAuthV2.prototype.isAvailable = function (successCallback, errorCallback) {
    cordova.exec(
        successCallback,
        errorCallback,
        "FingerprintAuthV2",  // Java Class
        "availability", // action
        [{}]
    );
};

FingerprintAuthV2 = new FingerprintAuthV2();
module.exports = FingerprintAuthV2;