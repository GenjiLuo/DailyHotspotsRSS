package org.gallon.rss.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.nio.charset.StandardCharsets

@Configuration
class RestTemplateConfig {

  @Bean
  fun restTemplate(): RestTemplate {
    val restTemplate = RestTemplate()
    restTemplate.requestFactory = SimpleClientHttpRequestFactory().apply { setOutputStreaming(false) }
    restTemplate.errorHandler = ThrowErrorHandler()
    return restTemplate
  }


}