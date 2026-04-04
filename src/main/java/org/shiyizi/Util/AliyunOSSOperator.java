package org.shiyizi.Util;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Component
public class AliyunOSSOperator {

    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endpointConfig;
    
    @Value("${spring.cloud.alicloud.oss.bucket-name}")
    private String bucketNameConfig;
    
    @Value("${spring.cloud.alicloud.oss.region}")
    private String regionConfig;
    
    private static String endpoint;
    private static String bucketName;
    private static String region;

    @PostConstruct
    public void init() {
        endpoint = this.endpointConfig;
        bucketName = this.bucketNameConfig;
        region = this.regionConfig;
        log.info("阿里云 OSS 初始化完成 - Endpoint: {}, Bucket: {}, Region: {}", endpoint, bucketName, region);
    }

    public String upload(byte[] content, String originalFilename) throws Exception {
        if (content == null || content.length == 0) {
            throw new IllegalArgumentException("上传内容不能为空");
        }
        
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new IllegalArgumentException("文件名不能为空");
        }

        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        String fileExtension = getFileExtension(originalFilename);
        String newFileName = UUID.randomUUID() + fileExtension;
        String objectName = dir + "/" + newFileName;

        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        
        OSS ossClient = null;
        try {
            ossClient = OSSClientBuilder.create()
                    .endpoint(endpoint)
                    .credentialsProvider(credentialsProvider)
                    .clientConfiguration(clientBuilderConfiguration)
                    .region(region)
                    .build();

            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
            log.info("文件上传成功：{}", objectName);
            
            return buildFileUrl(objectName);
        } catch (Exception e) {
            log.error("文件上传失败：{}", objectName, e);
            throw new RuntimeException("文件上传失败：" + e.getMessage(), e);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    public void delete(String url) {
        if (url == null || url.isEmpty()) {
            log.warn("删除失败，URL 为空");
            return;
        }
        
        try {
            String objectName = extractObjectName(url);
            
            EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
            ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
            clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
            
            OSS ossClient = null;
            try {
                ossClient = OSSClientBuilder.create()
                        .endpoint(endpoint)
                        .credentialsProvider(credentialsProvider)
                        .clientConfiguration(clientBuilderConfiguration)
                        .region(region)
                        .build();
                
                ossClient.deleteObject(bucketName, objectName);
                log.info("文件删除成功：{}", objectName);
            } finally {
                if (ossClient != null) {
                    ossClient.shutdown();
                }
            }
        } catch (Exception e) {
            log.error("文件删除失败：{}", url, e);
            throw new RuntimeException("文件删除失败：" + e.getMessage(), e);
        }
    }
    
    private String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            return "";
        }
        return filename.substring(lastDotIndex);
    }
    
    private String buildFileUrl(String objectName) {
        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;
    }
    
    private String extractObjectName(String url) {
        String[] parts = url.split("/");
        if (parts.length < 2) {
            throw new IllegalArgumentException("无效的 URL 格式");
        }
        
        String fileNameWithExtension = parts[parts.length - 1];
        String dateDir = parts[parts.length - 2];
        
        return dateDir + "/" + fileNameWithExtension;
    }
}
