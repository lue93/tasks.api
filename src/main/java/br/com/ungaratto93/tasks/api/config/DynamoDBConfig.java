package br.com.ungaratto93.tasks.api.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

@Configuration
@EnableDynamoDBRepositories("br.com.ungaratto93.tasks.api")
public class DynamoDBConfig {
    @Value("${aws.dynamodb.endpoint}")
    private String awsEndpoint;
    @Value("${aws.accesskey}")
    private String awsAccessKey;
    @Value("${aws.secretkey}")
    private String awsSecretKey;
    @Value("${aws.region}")
    private String awsRegion;
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());

        if (!StringUtils.isEmpty(awsEndpoint)) {
            amazonDynamoDB.setEndpoint(awsEndpoint);
        }

        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
                awsAccessKey, awsSecretKey);
    }
}