package com.ukcorp.ieum.member.entity.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;

/**
 * List를 DB에 저장하기 위한 Converter
 */
@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        // DB 저장을 위해 List<String>을 문자열로 변환
        return String.join(",", attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        // DB에 저장된 문자열을 List<String>으로 변환
        return Arrays.asList(dbData.split(","));
    }
}
