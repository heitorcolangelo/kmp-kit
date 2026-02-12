# Security Policy

## Supported Versions

| Version | Supported          |
| ------- | ------------------ |
| 0.1.5   | :white_check_mark: |

## Verifying Signed SBOMs

All releases from 0.1.5 onwards include signed Software Bill of Materials (SBOM) files. To verify the authenticity of an SBOM:

1. Install [Cosign](https://docs.sigstore.dev/cosign/installation/)
2. Download both the `*-sbom.json` and `*-sbom.bundle` files from the release
3. Run: `cosign verify-blob --bundle <bundle-file> <json-file>`

For detailed instructions, see the [README.md](README.md#verifying-signed-sbom).

## Reporting a Vulnerability

Please open an issue 
