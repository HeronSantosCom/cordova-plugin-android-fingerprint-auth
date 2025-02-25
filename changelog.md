# Version 2.0.1
### What's New
* Corrigido o acesso ao FINGERPRINT_ERROR_USER_CANCELED;
* Corrigido o acesso ao onCancelled();

# Version 2.0.0
### What's New
* Updates and renamed by [solidonline@b03b4c7491d7a7e4e26df8b57bfe0835617f9cf committed on Aug 5, 2022](https://github.com/solidonline/cordova-plugin-android-fingerprint-auth/commit/2b03b4c7491d7a7e4e26df8b57bfe0835617f9cf)
* Updates and renamed by [Aleksandr committed on May 25, 2020](https://github.com/solidonline/cordova-plugin-android-fingerprint-auth/commit/147b9f27b205a5975097a674b31e1fe25ec605aa)

# Version 1.5.1
### What's New
* Fixed by [benjamin committed on Jun 17, 2020](https://github.com/edorex/cordova-plugin-android-fingerprint-auth/commit/1ffa86fd635dbbe72d949773c855aea7a53c33f1)
* Fixed by [aliitotz@99194dd497dc22026dfa15662d84545f411ca0b3 committed on Oct 7, 2021](https://github.com/aliitotz/cordova-plugin-android-fingerprint-auth/commit/99194dd497dc22026dfa15662d84545f411ca0b3)
* Fixed by [Benjamin Lüscher committed on Dec 13, 2021](https://github.com/edorex/cordova-plugin-android-fingerprint-auth/commit/7a2a20608411cd01bd0200d2c25f080071d31e8a)
* Fixed by [benjamin-luescher committed on Dec 14, 2021](https://github.com/edorex/cordova-plugin-android-fingerprint-auth/commit/d3666121840cf4ed2dc412de804fd327d25d773d)
* Fixed by [Benjamin Lüscher committed on Feb 8, 2022](https://github.com/edorex/cordova-plugin-android-fingerprint-auth/commit/867819311dc542e091232c51ad3091c25cf00624)
* Fixed by [Benjamin Lüscher committed on Apr 4, 2022](https://github.com/edorex/cordova-plugin-android-fingerprint-auth/commit/d02a46acdd8820603634f66b15550e8993c7746b)
* Fixed by [pinionpi@4ca0fcabd169be4596ed1d7a276ced91bf33b0d2 committed on Apr 7, 2023](https://github.com/pinionpi/cordova-plugin-android-fingerprint-auth/commit/4ca0fcabd169be4596ed1d7a276ced91bf33b0d2)

# Version 1.5.0
### What's New
* Added new `dismiss()` action

# Version 1.4.5
### What's New
* Added Korean translations
    * merged pull request #125
* Added index.d.ts for Typescript support
    * merged pull request #118
* Update German strings
    * merged pull request #117

# Version 1.4.4
### What's New
* Added Arabic translations
    * merged pull request #114
* Bug fix: KeyStore not initialized when trying to delete entry
    * merged pull request #106

# Version 1.4.3
### What's New
* Added Thai translations
    * merged pull request #102


# Version 1.4.2
### What's New
* Feature request for issue #100
    * added back the `userAuthRequired` param but set default to `false`

# Version 1.4.1
### What's New
* **Bug fix** for issue #98
    * Use `resource-file` tag for resources instead of `source-file`


# Version 1.4.0
### What's New
* new parameter `encryptNoAuth`
    * Encrypt input without displaying authentication dialog
* removed parameter `userAuthRequired`
    * userAuthRequired will always be set to `false`
        * This fixes issue #85 and issue #88
        * Developers using default setting for this value (did not explicitly set `userAuthRequired` to `false`) should add a call to `FingerprintAuth.delete()` and re-encrypt credentials to regenerate a new secret key that will allow backup authentication to encrypt/decrypt using the new secret key.  This should fix any `ILLEGAL_BLOCK_SIZE_EXCEPTION` messages when using backup authentication.
* changed credential delimiter
    * backwards compatible - will be able to decrypt tokens created by prior versions of this plugin.

# Version 1.3.1
### What's New
* **Bug fix** for issue #86

# Version 1.3.0
### What's New
* Fixed issue #85 No token returned when using PIN backup
    * Authentication with backup credentials will now use cryptography to encrypt or decrypt a token.

# Version 1.2.8
### What's New
* Updates to README
* Merge pull request #66 from videmort/master: Update Spanish literal
* Merge pull request #65 from nataze/backup-PIN: PIN fallback when fingerprint isn't supported
* **Bug fix** for issue #54
    * Allow state loss of dialog fragment
* Changed manifest permission check
    * Now using cordova.hasPermission()
    * Removed dependency on android.support.v4 library
        * Removed build-extras.gradle

# Version 1.2.7
### What's New
* Improved German translations - pull request #58
* **Bug fix** for issue #57 - deleting secret key in Android Keystore.
* Added fixed error codes - pull request #56
* Added `ERRORS` JSON Object to the FingerprintAuth class prototype with the following fields corresponding to the new fixed error codes:
    ```
     BAD_PADDING_EXCEPTION,
     CERTIFICATE_EXCEPTION,
     FINGERPRINT_CANCELLED,
     FINGERPRINT_DATA_NOT_DELETED,
     FINGERPRINT_ERROR,
     FINGERPRINT_NOT_AVAILABLE,
     FINGERPRINT_PERMISSION_DENIED,
     FINGERPRINT_PERMISSION_DENIED_SHOW_REQUEST,
     ILLEGAL_BLOCK_SIZE_EXCEPTION,
     INIT_CIPHER_FAILED,
     INVALID_ALGORITHM_PARAMETER_EXCEPTION,
     IO_EXCEPTION,
     JSON_EXCEPTION,
     MINIMUM_SDK,
     MISSING_ACTION_PARAMETERS,
     MISSING_PARAMETERS,
     NO_SUCH_ALGORITHM_EXCEPTION,
     SECURITY_EXCEPTION
    ```

### Breaking Changes
* Changed error message for cancelled from "Cancelled" to fixed error code `FingerprintAuth.ERRORS.FINGERPRINT_CANCELLED`

# Version 1.2.6
### What's New
* **Bug fix** for issue #61 - added missing source-file element for German strings to plugin.xml

# Version 1.2.5
### What's New
* **Bug fix** for issue #46 - Dismiss fragment in a safer way

# Version 1.2.4
### What's New
* Updated `build-extras.gradle` to use Android SDK 25.

# Version 1.2.3
### What's New
* German translations

# Version 1.2.2
### What's New
* **Bug fix** - `isAvailable()` returning message "Missing required parameters".
* Added `build-extras.gradle` to add dependency `com.android.support:support-v4:23.0.0`  to check for manifest permissions.
* Added check and request for permission to use fingerprints.
* Added error handling for `SecurityException`

# Version 1.2.0
### What's New
* Removed `FingerprintAuth.CipherMode`
* Removed `FingerprintAuth.show() ` in favor of separate actions for encrypt and decrypt
* Added `FingerprintAuth.encrypt()`
* Added `FingerprintAuth.decrypt()`
* Made `username` optional
* `token` is required for `decrypt()`

### Breaking Changes
* Removed `FingerprintAuth.CipherMode`
* Removed `FingerprintAuth.show() ` in favor of separate actions for encrypt and decrypt


# Version 1.1.0
Introducing encryption and decryption of user credentials.
### What's New
* **Added parameters to the FingerprintAuth Config Object**

| Param | Type | Description |
| --- | --- | --- |
| username | String | (REQUIRED) Used to create credential string for encrypted token and as alias to retrieve the cipher. |
| cipherMode | FingerprintAuth.CipherMode | (REQUIRED) Used to determine if plugin should encrypt or decrypt after authentication. <br/><ul><li>FingerprintAuth.CipherMode.ENCRYPT</li><li>FingerprintAuth.CipherMode.DECRYPT</li></ul>|
| password | String |  Used to create credential string for encrypted token |
| token | String  | Used to create credential string for encrypted token. |

* **Changed FingerprintAuth.show() Result fields**

| Param | Type  | Description |
| --- | --- | ---  |
| withFingerprint | boolean | `true` if user authenticated using a fingerprint |
| withBackup | boolean | `true` if user used the backup credentials activity to authenticate. |
| cipherMode | FingerprintAuth.CipherMode | Pass through parameter from config object. |
| token | String | Will contain the base64 encoded credentials if `withFingerprint == true` and `cipherMode == FingerprintAuth.CipherMode.ENCRYPT`. |
| password | String | Will contain the decrypted password if `withFingerprint == true` and `cipherMode == FingerprintAuth.CipherMode.DECRYPT` 

* **New method FingerprintAuth.delete() to delete the cipher used to encrypt/decrypt user credentials.**

### Breaking changes

* Removed `clientSecret` parameter from the FingerprintAuth Config Object.
* Added new **required parameters** `cipherMode` and `username`.
* FingerprintAuth.show() result `withFingerprint` is now a boolean.  You will need to obtain the encrypted token from the `token` field.
* FingerprintAuth.show() result `withPassword` was changed to `withBackup`
