package com.binarycube.bp.cloud;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import com.binarycube.bp.cloud.BusPaymentCore;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Unit tests for Cloud components
 */
// [START example]
@RunWith(JUnit4.class)
public class BusPaymentCloudTest {
  private static final String FAKE_URL = "fake.fk/hello";
  // Set up a helper so that the ApiProxy returns a valid environment for local testing.
  private final LocalServiceTestHelper helper = new LocalServiceTestHelper();

  @Mock private HttpServletRequest mockRequest;
  @Mock private HttpServletResponse mockResponse;
  private StringWriter responseWriter;
  private BusPaymentCore servletUnderTest;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    helper.setUp();

    //  Set up some fake HTTP requests
    when(mockRequest.getRequestURI()).thenReturn(FAKE_URL);

    // Set up a fake HTTP response.
    responseWriter = new StringWriter();
    when(mockResponse.getWriter()).thenReturn(new PrintWriter(responseWriter));

    servletUnderTest = new BusPaymentCore();
  }

  @After public void tearDown() {
    helper.tearDown();
  }

  @Test
  public void doGetWritesResponse() throws Exception {
    servletUnderTest.doGet(mockRequest, mockResponse);

    // We expect our hello world response.
    assertThat(responseWriter.toString())
        .named("HelloAppEngine response")
        .contains("Hello App Engine - Standard ");
  }

  @Test
  public void helloInfoTest() {
    String result = BusPaymentCore.getInfo();
    assertThat(result)
      .named("HelloInfo.getInfo")
      .containsMatch("^Version:\\s+.+OS:\\s+.+User:\\s");
  }
}
// [END example]