# Agent guide — kmp-kit

This document orients automated assistants and contributors who work on this repository.

## What this is

**kmp-kit** is a Kotlin Multiplatform (KMP) library project. The published artifact is the **`core`** module (`com.kmpkit`). It targets **Android** (via the Android KMP library plugin) and **iOS** (`iosX64`, `iosArm64`, `iosSimulatorArm64`). The root `README.md` focuses on SBOM download and Cosign verification for consumers.

## Tech stack

| Area | Notes |
|------|--------|
| Kotlin | Version pinned in `gradle/libs.versions.toml` (see `[versions].kotlin`). |
| AGP | Android Gradle Plugin version in `[versions].agp`. |
| Java | CI uses **Temurin 21** (see `.github/workflows/*.yml`). |
| SBOM | CycloneDX Gradle plugin; SBOM artifacts attach to Maven publications. |
| Style | `kotlin.code.style=official` in `gradle.properties`. |

Authoritative versions: **`gradle/libs.versions.toml`**. Prefer adding or bumping dependencies there rather than hard-coding versions in `build.gradle.kts` files.

## Layout

| Path | Role |
|------|------|
| `settings.gradle.kts` | Root name `kmp-kit`; includes `:core`. |
| `build.gradle.kts` | Root: `maven-publish`, CycloneDX, shared publishing repo (GitHub Packages), SBOM artifact wiring for subprojects. |
| `core/build.gradle.kts` | KMP + Android library: `namespace`, SDK levels, iOS targets, `commonMain` / `commonTest` dependencies. |
| `core/src/commonMain/`, `androidMain/`, `iosMain/` | Shared and platform-specific Kotlin. |
| `core/src/commonTest/` | Multiplatform tests (`kotlin-test`). |
| `.github/workflows/` | PR validation, `main` branch build, release publish, Release Drafter. |

Package namespace for library code: **`com.kmpkit`**. Platform APIs use **`expect`/`actual`** (see `Platform.kt` and platform sources).

## Commands

Run from the repository root (use the Gradle wrapper: `./gradlew`).

| Task | Command |
|------|---------|
| Assemble | `./gradlew assemble` |
| All KMP tests | `./gradlew allTests` |
| Generate SBOM | `./gradlew cyclonedxBom` |
| Publish (needs credentials; see below) | `./gradlew publish` — releases often pass `-Pversion=<tag>` |

Default development version is in `gradle.properties` (`version=...`).

## CI and releases

- **PRs and `main`**: Ubuntu, Java 21, `./gradlew assemble` and `./gradlew allTests`.
- **Publish workflow** (`.github/workflows/publish-release.yml`): runs on **macOS** (iOS-related toolchain), generates SBOM, signs with **Cosign**, then publishes with `PUBLISHING_TOKEN` and version from the Git tag.

Do not assume you can publish or sign from a local sandbox without the same secrets and environment.

## Conventions for changes

- Keep **`gradle/libs.versions.toml`** as the single source for plugin and library versions when adding dependencies.
- Match existing **KMP source set layout** (`commonMain`, `androidMain`, `iosMain`, `commonTest`); use `expect`/`actual` when behavior differs by platform.
- Android: **`namespace`** and SDK fields live in `core/build.gradle.kts` — align changes with AGP/KMP expectations.
- Root `build.gradle.kts` configures **CycloneDX** `includeConfigs` / `skipConfigs`; if dependency scopes change in a way that affects SBOM, review those settings.
- Avoid unrelated refactors, drive-by formatting-only edits, or new documentation files unless the task explicitly requires them.

## Publishing and SBOM (high level)

Artifacts go to **GitHub Packages** (`build.gradle.kts` repository URL). Publications include CycloneDX SBOM artifacts; the release workflow signs the SBOM with Cosign. For verification steps and consumer-facing SBOM docs, see **`README.md`**.
