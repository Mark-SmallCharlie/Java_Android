# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

LoginView is a pure-Java Android application implementing a login and registration UI. Chinese-language app (Simplified Chinese strings). No backend — login is cosmetic (Toast only, no actual auth).

## Build & Run

```bash
# Build debug APK
./gradlew assembleDebug

# Install to connected device/emulator
adb install -r app/build/outputs/apk/debug/app-debug.apk

# Run unit tests
./gradlew test

# Run instrumented tests (requires emulator/device)
./gradlew connectedAndroidTest
```

SDK path is configured in `local.properties` (`D:\Android\android-sdk`). Emulator AVD: `Resizable_Experimental_API_34`.

## Tech Stack

- **Language:** Java 1.8 (no Kotlin)
- **Build:** Gradle 8.0, AGP 8.1.3, Kotlin DSL build scripts
- **SDK:** compileSdk 33, minSdk 28, targetSdk 33
- **UI:** Material 3 (`Theme.Material3.DayNight.NoActionBar`), ConstraintLayout, CardView, TextInputLayout
- **Testing:** JUnit 4 (unit), Espresso (instrumented) — both are boilerplate only

## Architecture

Flat, Activity-centric. No ViewModels, Repositories, or separation layers. Each Activity follows:

- `initViews()` — binds views via `findViewById`
- `initListeners()` — sets click handlers with inline logic

Two screens:
- **`MainActivity`** — login form (account + password), register button, QQ/WeChat social placeholders
- **`RegisterActivity`** — registration form (name, email, password, confirm, gender RadioGroup, 5 hobby CheckBoxes), form validation, AlertDialog on success

## Package & Naming

Package: `com.example.loginview` (flat, no sub-packages).

Conventions:
- View IDs: `snake_case` with Hungarian type prefix (`et_account`, `btn_login`, `til_account`, `rg_gender`, `cb_reading`)
- Layouts: `activity_<name>.xml`
- Drawables: `ic_<name>.xml` / `ic_<name>.png`
- Strings: snake_case, grouped by feature (`toast_*`, `error_*`, `hint_*`, `btn_*`, `label_*`, `hobby_*`)

## Key Notes

- `getSelectedHobbies()` in `RegisterActivity` is dead code (defined but never called)
- Social login buttons (QQ, WeChat) are placeholders — show Toast only
- Night theme is configured (`values-night/themes.xml`) with same color structure
