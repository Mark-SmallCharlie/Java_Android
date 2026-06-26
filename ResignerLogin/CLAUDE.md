# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

学籍信息管理系统 (Student Information Management System) — a native Android app that registers and authenticates students. The app connects **directly** to a Microsoft SQL Server database over JDBC from the Android device (no backend server layer).

## Build & Run

```bash
# Build debug APK
./gradlew assembleDebug

# Install on connected device/emulator
./gradlew installDebug

# Run unit tests
./gradlew test

# Run instrumented tests (requires device/emulator)
./gradlew connectedAndroidTest
```

## Architecture

Single-module Android app (`:app`), pure Java (no Kotlin), Java 8 compatibility.

Three classes in `com.example.resignerlogin`:

- **`DatabaseHelper.java`** — Static utility class managing SQL Server connections via JDBC. Contains hardcoded connection params (IP, credentials, port 1433). Provides `getConnection()`, `registerStudent()`, `verifyLogin()`.
- **`MainActivity.java`** — Login screen. Runs DB queries on `ExecutorService`, posts results back via `Handler`.
- **`RegisterActivity.java`** — Registration screen. Generates random student ID ("2023" + 6 digits). Single-click guard prevents duplicate submissions.

UI: Two `LinearLayout`-based screens, Material3 DayNight theme, purple accent (`#6750A4`). The `logo.jpg` drawable is the school logo shown on both screens.

## Key Technical Details

- **Database**: SQL Server accessed via `mssql-jdbc:12.4.2.jre11` bundled in the APK. Connection string uses `encrypt=false` and `trustServerCertificate=true`.
- **SQL table**: `Students` with columns `StudentId, Name, Email, Password, Gender, Hobbies`.
- **Network requirement**: Device must be on the same local network as the SQL Server instance. The server IP in `DatabaseHelper.java` must match the current network environment.
- **Permissions**: `INTERNET` and `ACCESS_NETWORK_STATE` declared in manifest.
- **StrictMode**: `permitAll()` is set globally in `DatabaseHelper`'s static initializer.
- `constraintlayout` is in dependencies but unused — both layouts use `LinearLayout`.
- `MainActivity` has static fields `registeredAccount`/`registeredPassword` that are set by `RegisterActivity` but never read for login (login always queries the database).
