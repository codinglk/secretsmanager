# secretsmanager
AWS Secrets Manager - AWS Java SDK POC

Follow the below mentioned simple steps

Step1: Login to AWS Management Console, Go to AWS Secrets Manager and create secrets

Step2: Grant permissions to a role that represents an application. For example, an application running on an Amazon EC2 instance

AWS Documentation: https://docs.aws.amazon.com/secretsmanager/latest/userguide/auth-and-access_iam-policies.html

Step3: Add Below dependency in your Spring Boot project 

<!-- https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk-secretsmanager -->

<dependency>

	<groupId>com.amazonaws</groupId>

	<artifactId>aws-java-sdk-secretsmanager</artifactId>

	<version>1.12.227</version>

</dependency>

Step4: Add Get Secrets Code in the project and provide the secretName and region which you have provided when created secrets.

// Use this code snippet in your app

// If you need more information about configurations or implementing the sample code, visit the AWS docs:

// https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/java-dg-samples.html#prerequisites

  public static void getSecret() {

    String secretName = "AWSSecretsManagerPOC";

    String region = "us-west-2";

// Create a Secrets Manager client

    AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()

        .withRegion(region)

        .build();

// In this sample we only handle the specific exceptions for the 'GetSecretValue' API.

// See https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html

// We rethrow the exception by default.

String secret, decodedBinarySecret;

GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()

        .withSecretId(secretName);

    GetSecretValueResult getSecretValueResult = null;

 try {

      getSecretValueResult = client.getSecretValue(getSecretValueRequest);

    } catch (DecryptionFailureException e) {

// Secrets Manager can't decrypt the protected secret text using the provided KMS key.

// Deal with the exception here, and/or rethrow at your discretion.

      throw e;

    } catch (InternalServiceErrorException e) {

// An error occurred on the server side.

// Deal with the exception here, and/or rethrow at your discretion.

      throw e;

    } catch (InvalidParameterException e) {

// You provided an invalid value for a parameter.

// Deal with the exception here, and/or rethrow at your discretion.

      throw e;

    } catch (InvalidRequestException e) {

// You provided a parameter value that is not valid for the current state of the resource.

// Deal with the exception here, and/or rethrow at your discretion.

      throw e;

    } catch (ResourceNotFoundException e) {

// We can't find the resource that you asked for.

// Deal with the exception here, and/or rethrow at your discretion.

      throw e;

    }

// Decrypts secret using the associated KMS key.

// Depending on whether the secret is a string or binary, one of these fields will be populated.

    if (getSecretValueResult.getSecretString() != null) {

      secret = getSecretValueResult.getSecretString();

      System.out.println("secret - " + secret);

    } else {

      decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());

      System.out.println("decodedBinarySecret - " + decodedBinarySecret);

    }

// Your code goes here.

  }

Step5: That’s it! You have got the secrets from AWS Secrets Manager and can use in your project now.

Thanks!

Lalit Kumar

https://codinglk.com
