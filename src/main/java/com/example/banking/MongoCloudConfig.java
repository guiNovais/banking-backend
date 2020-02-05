package com.example.banking;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Base64;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

/**
 * MongoCloudConfig
 */
@Profile("cloud")
@Configuration
public class MongoCloudConfig extends AbstractMongoClientConfiguration {

    @Value("${vcap.services.mongo.credentials.connection.mongodb.certificate.certificate_base64}")
    private String certificateBase64;

    @Value("${vcap.services.mongo.credentials.connection.mongodb.composed[0]}")
    private String composed;

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(composed);
        byte[] certificate = Base64.getDecoder().decode(certificateBase64);

        return MongoClients.create(
                MongoClientSettings.builder().applyConnectionString(connectionString).applyToSslSettings(builder -> {
                    builder.enabled(true);
                    try {
                        builder.context(CryptoUtil.buildSslContext(new ByteArrayInputStream(certificate)));
                    } catch (KeyManagementException | CertificateException | KeyStoreException
                            | NoSuchAlgorithmException | IOException e) {
                        e.printStackTrace();
                    }
                }).build());
    }

    @Override
    protected String getDatabaseName() {
        return "data";
    }
}