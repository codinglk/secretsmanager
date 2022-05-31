package com.codinglk.secretsmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.codinglk.secretsmanager.util.SecretsManager;

/**
 * @author codinglk
 */
@SpringBootApplication
public class SecretsManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecretsManagerApplication.class, args);
        System.out.println("AWS Secrets Manager POC - AWS Java SDK");
        SecretsManager.getSecret();
    }

}
