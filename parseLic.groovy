import groovy.xml.XmlSlurper

def xmlText = '''<Document stan="v:sksb_1_6.2024-02-26 10-06-47.596">
	<dsig:Object Id="mainData" xmlns="" xmlns:dsig="http://www.w3.org/2000/01/xmldsig">
		<license document_type="a">
			<version>3.4.0</version>
			<services>
				<service name="IDE.BASIC"/>
				<service name="IDE.PLATFORM"/>
				<service name="PLPCHECK"/>
			</services>
			<startDate>2020-04-29T20:38:25+0700</startDate>
			<endDate>2024-05-29T14:48:17+0700</endDate>
			<userFIO>Cлужебный пользователь для Открытия</userFIO>
			<userMail>xxxxxxxxxx@open.ru</userMail>
			<organization>ПАО ФК Открытие (бывш.НОМОС-Банк)</organization>
			<workstations>
				<workstation>PHdvPg==</workstation>
				<workstation>PHdvuPg==</workstation>
				<workstation>PHdbj4=</workstation>
			</workstations>
		</license>
	</dsig:Object>
	<Manifest Id="metaData">
		<Reference URI="#mainData">
			<Transforms>
				<Transform Algorithm="http://www.w3.org/TR/1999/WD-xml-c14n-19991115"/>
			</Transforms>
			<DigestMethod Algorithm="http://www.w3.org/2000/01/xmldsig/md5"/>
			<DigestValue>TcQ==</DigestValue>
		</Reference>
	</Manifest>
	<Signature Id="Sig1" xmlns="http://www.w3.org/2000/01/xmldsig/">
		<SignedInfo>
			<CanonicalizationMethod Algorithm="http://www.w3.org/TR/1999/WD-xml-c14n-19991115"/>
			<SignatureMethod Algorithm="http://www.w3.org/2000/01/xmldsig/rsa-sha1"/>
			<Reference URI="#metaData">
				<Transforms>
					<Transform Algorithm="http://www.w3.org/TR/1999/WD-xml-c14n-19991115"/>
				</Transforms>
				<DigestMethod Algorithm="http://www.w3.org/2000/01/xmldsig/md5"/>
				<DigestValue>PQ==</DigestValue>
			</Reference>
			<Reference URI="#Obj1">
				<Transforms>
					<Transform Algorithm="http://www.w3.org/TR/1999/WD-xml-c14n-19991115"/>
				</Transforms>
				<DigestMethod Algorithm="http://www.w3.org/2000/01/xmldsig/md5"/>
				<DigestValue>nA9g==</DigestValue>
			</Reference>
		</SignedInfo>
		<SignatureValue>Kvdso=</SignatureValue>
		<KeyInfo>
			<X509Data>
				<X509SubjectName>CN=CFT Platform IDE Licensor Reserve, OU=Upravlenie po razvitiju Platformy, O=ZAO CFT, L=Novosibirsk, C=RU</X509SubjectName>
				<X509Certificate>MIIDHR3Q==</X509Certificate>
			</X509Data>
		</KeyInfo>
		<dsig:Object Id="Obj1" xmlns="" xmlns:dsig="http://www.w3.org/2000/01/xmldsig">
			<SignatureProperties>
				<SignatureProperty>
					<date>2024-02-26</date>
					<time>03:06:47.596</time>
					<ranumber>57Q==</ranumber>
					<version>1,18,0,0</version>
				</SignatureProperty>
			</SignatureProperties>
		</dsig:Object>
	</Signature>
</Document>'''

def xmlSlurper = new XmlSlurper(false, false)
xmlSlurper.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
def documentNode = xmlSlurper.parseText(xmlText)

// Преобразование XML в объекты
def document = new Document(
    stan: documentNode.@stan.text(),
    dsigObject: new DsigObject(
        id: documentNode.'dsig:Object'.@Id.text(),
        license: new License(
            documentType: documentNode.'dsig:Object'.license.@document_type.text(),
            version: documentNode.'dsig:Object'.license.version.text(),
            services: documentNode.'dsig:Object'.license.services.service.collect {
                new Service(name: it.@name.text())
            },
            startDate: documentNode.'dsig:Object'.license.startDate.text(),
            endDate: documentNode.'dsig:Object'.license.endDate.text(),
            userFIO: documentNode.'dsig:Object'.license.userFIO.text(),
            userMail: documentNode.'dsig:Object'.license.userMail.text(),
            organization: documentNode.'dsig:Object'.license.organization.text(),
            workstations: documentNode.'dsig:Object'.license.workstations.workstation*.text()
        )
    ),
    manifest: new Manifest(
        id: documentNode.Manifest.@Id.text(),
        reference: new Reference(
            uri: documentNode.Manifest.Reference.@URI.text(),
            transforms: documentNode.Manifest.Reference.Transforms.Transform.collect {
                new Transform(algorithm: it.@Algorithm.text())
            },
            digestMethod: new DigestMethod(
                algorithm: documentNode.Manifest.Reference.DigestMethod.@Algorithm.text()
            ),
            digestValue: documentNode.Manifest.Reference.DigestValue.text()
        )
    ),
    signature: new Signature(
        id: documentNode.Signature.@Id.text(),
        signedInfo: new SignedInfo(
            canonicalizationMethod: documentNode.Signature.SignedInfo.CanonicalizationMethod.@Algorithm.text(),
            signatureMethod: documentNode.Signature.SignedInfo.SignatureMethod.@Algorithm.text(),
            references: documentNode.Signature.SignedInfo.Reference.collect {
                new Reference(
                    uri: it.@URI.text(),
                    transforms: it.Transforms.Transform.collect {
                        new Transform(algorithm: it.@Algorithm.text())
                    },
                    digestMethod: new DigestMethod(
                        algorithm: it.DigestMethod.@Algorithm.text()
                    ),
                    digestValue: it.DigestValue.text()
                )
            }
        ),
        signatureValue: documentNode.Signature.SignatureValue.text(),
        keyInfo: new KeyInfo(
            x509Data: new X509Data(
                x509SubjectName: documentNode.Signature.KeyInfo.X509Data.X509SubjectName.text(),
                x509Certificate: documentNode.Signature.KeyInfo.X509Data.X509Certificate.text()
            )
        ),
        dsigObject: new DsigObject(
            id: documentNode.Signature.'dsig:Object'.@Id.text(),
            license: null // Здесь можно добавить логику для парсинга внутреннего объекта, если нужно
        )
    )
)

// Вывод результата
println(document)
