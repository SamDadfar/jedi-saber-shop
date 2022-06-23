package nl.hybrit.lightsaber.shop.security;

import nl.hybrit.lightsaber.shop.security.jwt.AuthEntryPointJwt;
import nl.hybrit.lightsaber.shop.security.jwt.AuthTokenFilter;
import nl.hybrit.lightsaber.shop.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // @Autowired
    // private Oauth2AuthenticationSuccessHandler oauthSuccessHandler;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    // @Autowired
    // private CustomOAuth2UserService OAuth2UserService;

//    @Autowired
//    private UserService UserService;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/Jedisabershop/**", "/h2-console/**").permitAll()
                .antMatchers("/api/auth/**", "/oauth/**").permitAll().antMatchers("/api/auth/test/**", "Jedisabershop/order/**").permitAll().anyRequest()
                .authenticated();
        http.headers().frameOptions().disable();
        // .and().oauth2Login().userInfoEndpoint().userService(OAuth2UserService)
        // .and().successHandler(new AuthenticationSuccessHandler(){

        //     @Override
        //     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        //             Authentication authentication) throws IOException, ServletException {
        //         CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        //         UserService.processOAuthPostLogin(oAuth2User.getEmail());
        //         response.sendRedirect("/oauth2/success");
        //     }

        // });

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }

}