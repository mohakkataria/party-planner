package com.partyplanner.wiremocks;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.junit.Assert;

public class CustomerDataWireMock extends WireMockClassRule {

  public CustomerDataWireMock(Options options) {
    super(options);
  }

  protected static String getFileData(String path) {
    String content = null;
    try {
      content = new String(getFileBytes(path), StandardCharsets.UTF_8.name());
    } catch (IOException e) {
      e.printStackTrace();
    }
    Assert.assertNotNull(content);
    return content;
  }

  protected static byte[] getFileBytes(String path) throws IOException {
    ClassLoader classLoader = CustomerDataWireMock.class.getClassLoader();
    URL resource = classLoader.getResource(path);
    Assert.assertNotNull(resource);
    File file = new File(URLDecoder.decode(resource.getFile()));
    Assert.assertTrue(file.exists());
    return Files.readAllBytes(file.toPath());
  }

  @Override
  protected void before() {
    super.before();

    for(int i = 0; i <= 3; i++) {
      this.stubFor(
        get(urlPathMatching("/customer" + i + ".txt"))
          .willReturn(aResponse()
            .withBody(getFileData("mockdata/customer" + i + ".txt"))
            .withStatus(200)
            .withHeader("Content-Type", "text/plain")
          ));
    }
  }
}
