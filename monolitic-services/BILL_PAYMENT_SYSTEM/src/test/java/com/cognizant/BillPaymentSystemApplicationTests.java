package com.cognizant;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.util.WebUtils;

@SpringBootTest
class BillPaymentSystemApplicationTests {

                private BasicAuthenticationFilter filter;
                private AuthenticationManager manager;

                @BeforeEach
                void setUp() throws Exception {
                                SecurityContextHolder.clearContext();
                                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken("user", "user");
                                authRequest.setDetails(new WebAuthenticationDetails(new MockHttpServletRequest()));
                                Authentication authentication = new UsernamePasswordAuthenticationToken("user", "user",
                                                                AuthorityUtils.createAuthorityList("ROLE_USER"));
                                manager = mock(AuthenticationManager.class);
                                when(manager.authenticate(authRequest)).thenReturn(authentication);
                                // when(manager.authenticate(not(eq(authRequest)))).thenThrow(new
                                // BadCredentialsException(""));
                                filter = new BasicAuthenticationFilter(manager);
                }

                @AfterEach
                void clearContext() {
                                SecurityContextHolder.clearContext();
                }

                @Test
                public void testFilterIgnoresRequestContainingNoAutherizationHeader() throws ServletException, IOException {
                                MockHttpServletRequest request = new MockHttpServletRequest();
                                request.setServletPath("/authenticate");
                                final MockHttpServletResponse response = new MockHttpServletResponse();
                                FilterChain chain = mock(FilterChain.class);
                                filter.doFilter(request, response, chain);
                                verify(chain).doFilter(request, response);
                                assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
                }

                @Test
                public void testInvalidBasicAuthorizationTokenIsIgnored() throws ServletException, IOException {
                                String token = "NOT_A_VALID_TOKEN_AS_MISSING_COLON";
                                MockHttpServletRequest request = new MockHttpServletRequest();
                                request.addHeader("Authorization", "Basic " + new String(Base64.encodeBase64(token.getBytes())));
                                request.setServletPath("/authenticate");
                                request.setSession(new MockHttpSession());
                                final MockHttpServletResponse response = new MockHttpServletResponse();
                                FilterChain chain = mock(FilterChain.class);
                                filter.doFilter(request, response, chain);
                                verify(chain, never()).doFilter(request, response);
                                assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
                                assertThat(response.getStatus()).isEqualTo(401);
                }

                @Test
                public void invalidBase64IsIgnored() throws Exception {
                                MockHttpServletRequest request = new MockHttpServletRequest();
                                request.addHeader("Authorization", "Basic NOT_VALID_BASE64");
                                request.setServletPath("/authenticate");
                                request.setSession(new MockHttpSession());
                                final MockHttpServletResponse response = new MockHttpServletResponse();

                                FilterChain chain = mock(FilterChain.class);
                                filter.doFilter(request, response, chain);
                                // The filter chain shouldn't proceed
                                verify(chain, never()).doFilter(request, response);
                                assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
                                assertThat(response.getStatus()).isEqualTo(401);
                }

                @Test
                public void testNormalOperation() throws Exception {
                                String token = "user:user";
                                MockHttpServletRequest request = new MockHttpServletRequest();
                                MockHttpServletResponse response = new MockHttpServletResponse();
                                request.addHeader("Authorization", "Basic " + new String(Base64.encodeBase64(token.getBytes())));
                                request.setServletPath("/authenticate");

                                // Test
                                assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
                                FilterChain chain = mock(FilterChain.class);
                                filter.doFilter(request, response, chain);

                                verify(chain).doFilter(request, response);
                                assertThat(SecurityContextHolder.getContext().getAuthentication()).isNotNull();
                                assertThat(SecurityContextHolder.getContext().getAuthentication().getName()).isEqualTo("user");
                }

                @Test
                public void testOtherAuthorizationSchemeIsIgnored() throws Exception {
                                MockHttpServletRequest request = new MockHttpServletRequest();
                                MockHttpServletResponse response = new MockHttpServletResponse();
                                request.addHeader("Authorization", "SOME_OTHER_AUTHENTICATION_SCHEME");
                                request.setServletPath("/some_file.html");
                                FilterChain chain = mock(FilterChain.class);
                                filter.doFilter(request, response, chain);

                                verify(chain).doFilter(request, response);
                                assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
                }

                @Test
                public void testSuccessLoginThenFailureLoginResultsInSessionLosingToken() throws Exception {
                                String token = "user:user";
                                MockHttpServletRequest request = new MockHttpServletRequest();
                                request.addHeader("Authorization", "Basic " + new String(Base64.encodeBase64(token.getBytes())));
                                request.setServletPath("/authenticate");
                                final MockHttpServletResponse response1 = new MockHttpServletResponse();

                                FilterChain chain = mock(FilterChain.class);
                                filter.doFilter(request, response1, chain);
                                verify(chain).doFilter(request, response1);
                                // Test
                                assertThat(SecurityContextHolder.getContext().getAuthentication()).isNotNull();
                                assertThat(SecurityContextHolder.getContext().getAuthentication().getName()).isEqualTo("user");

                                // NOW PERFORM FAILED AUTHENTICATION
                                token = "otherUser:WRONG_PASSWORD";
                                request = new MockHttpServletRequest();
                                request.addHeader("Authorization", "Basic " + new String(Base64.encodeBase64(token.getBytes())));
                                final MockHttpServletResponse response2 = new MockHttpServletResponse();
                                chain = mock(FilterChain.class);
                                filter.doFilter(request, response2, chain);
                                verify(chain, never()).doFilter(request, response2);
                                request.setServletPath("/authenticate");
                                // Test - the filter chain will not be invoked, as we get a 401 forbidden
                                // response
                                MockHttpServletResponse response = response2;
                                assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
                                assertThat(response.getStatus()).isEqualTo(401);
                }

                @Test
                public void skippedOnErrorDispatch() throws Exception {

                                String token = "bad:credentials";
                                MockHttpServletRequest request = new MockHttpServletRequest();
                                request.addHeader("Authorization", "Basic " + new String(Base64.encodeBase64(token.getBytes())));
                                request.setServletPath("/some_file.html");
                                request.setAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE, "/error");
                                MockHttpServletResponse response = new MockHttpServletResponse();

                                FilterChain chain = mock(FilterChain.class);

                                filter.doFilter(request, response, chain);

                                assertThat(response.getStatus()).isEqualTo(200);
                }

                @Test
                void contextLoads() {
                }

}

