class Document {
    String stan
    DsigObject dsigObject
    Manifest manifest
    Signature signature
}

class DsigObject {
    String id
    License license
}

class License {
    String documentType
    String version
    List<Service> services
    String startDate
    String endDate
    String userFIO
    String userMail
    String organization
    List<String> workstations
}

class Service {
    String name
}

class Manifest {
    String id
    Reference reference
}

class Reference {
    String uri
    List<Transform> transforms
    DigestMethod digestMethod
    String digestValue
}

class Transform {
    String algorithm
}

class DigestMethod {
    String algorithm
}

class Signature {
    String id
    SignedInfo signedInfo
    String signatureValue
    KeyInfo keyInfo
    DsigObject dsigObject
}

class SignedInfo {
    String canonicalizationMethod
    String signatureMethod
    List<Reference> references
}

class KeyInfo {
    X509Data x509Data
}

class X509Data {
    String x509SubjectName
    String x509Certificate
}
