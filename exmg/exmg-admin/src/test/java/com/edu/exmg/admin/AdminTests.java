package com.edu.exmg.admin;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminTests {


    @Test
    public void encryTest() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setPassword("9s1bnDggSHPEc_G3");
        config.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setConfig(config);

        System.out.println(encryptor.decrypt("2/MD6ZkNjbJ/w+3YvSm14s92VnbhAIUjltrXJuRmPUs="));
        System.out.println(encryptor.decrypt("m5nr6undT7+VPGpuqNBckaW63XveHG9v"));

    }

}
