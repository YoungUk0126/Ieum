package com.ukcorp.ieum.api.service;
/**
 * Google Service 인터페이슨
 *
 * @author : dachan
 * @fileName : GoogleService
 * @since : 01/22/24
 */
public interface GoogleService {
  byte[] convertTextToSpeech(String text) throws Exception;
}
