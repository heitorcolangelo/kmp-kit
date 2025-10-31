# Security Policy

## Supported Versions

Use this section to tell people about which versions of your project are
currently being supported with security updates.

| Version | Supported          |
| ------- | ------------------ |
| 5.1.x   | :white_check_mark: |
| 5.0.x   | :x:                |
| 4.0.x   | :white_check_mark: |
| < 4.0   | :x:                |

## Verifying Signed SBOMs

All releases include signed Software Bill of Materials (SBOM) files. To verify the authenticity of an SBOM:

1. Install [Cosign](https://docs.sigstore.dev/cosign/installation/)
2. Download both the `*-sbom.json` and `*-sbom.bundle` files from the release
3. Run: `cosign verify-blob --bundle <bundle-file> <json-file>`

For detailed instructions, see the [README.md](README.md#verifying-signed-sbom).

## Reporting a Vulnerability

Use this section to tell people how to report a vulnerability.

Tell them where to go, how often they can expect to get an update on a
reported vulnerability, what to expect if the vulnerability is accepted or
declined, etc.
