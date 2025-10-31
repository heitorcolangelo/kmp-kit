# KMP Kit

## Verifying Signed SBOM

This project publishes Software Bill of Materials (SBOM) files with cryptographic signatures using [Cosign](https://github.com/sigstore/cosign). This allows you to verify the authenticity and integrity of the SBOM files.

### What is an SBOM?

A Software Bill of Materials (SBOM) is a standardized list of all components, libraries, and dependencies that make up a software package. We use the CycloneDX format to provide transparency about the components in our releases.

### Downloading the SBOM Files

When you download a release from GitHub Packages or Maven, you'll find two SBOM-related artifacts:

- `*-sbom.json` - The SBOM file in JSON format
- `*-sbom.bundle` - The cryptographic signature bundle

### Verifying the Signature

To verify the signed SBOM, you'll need to have `cosign` installed. Install it following the [official instructions](https://docs.sigstore.dev/cosign/installation/).

#### Basic Verification

Once you have both files downloaded, verify the signature with:

```bash
cosign verify-blob \
  --bundle <path-to-sbom.bundle> \
  <path-to-sbom.json>
```

For example, if you downloaded the artifacts for version `1.0.0`:

```bash
cosign verify-blob \
  --bundle core-1.0.0-sbom.bundle \
  core-1.0.0-sbom.json
```

#### Expected Output

A successful verification will output:

```
Verified OK
```

This confirms that:
- The SBOM file has not been tampered with
- The signature was created by our GitHub Actions workflow
- The signature is valid and trusted through Sigstore's certificate authority

#### Verification Failure

If verification fails, you'll see an error message. Common causes:
- The files were corrupted during download
- The files don't match (wrong version or artifact)
- The signature bundle is invalid

### Keyless Signing

This project uses **keyless signing** with Cosign, which means:
- No long-term keys to manage
- Signatures are tied to GitHub Actions workflow identity
- Trust is established through Sigstore's transparency log and certificate authority
- Each signature includes proof of the workflow that created it

### Additional Resources

- [Cosign Documentation](https://docs.sigstore.dev/cosign/overview/)
- [CycloneDX Specification](https://cyclonedx.org/specification/overview/)
- [Sigstore Project](https://www.sigstore.dev/)
