package com.mark.test.framework.util;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mark .
 * Data   : 2019-05-08
 * faker 生成随机测试数据
 */
@Slf4j
public class FakerUtils {

    public static void getFakerInfo(){
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String streetAddress = faker.address().streetAddress();
        log.info(name + streetAddress);

    }

    public static void main(String[] args) {
        FakerUtils.getFakerInfo();
    }

}
