package com.colin.security;

/*
 * @EnableWebSecurity public class SecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Bean public UserDetailsService userDetailsService() { return new
 * UserDetailsServiceImpl(); }
 * 
 * @Bean public BCryptPasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * @Bean public DaoAuthenticationProvider authenticationProvider() {
 * DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
 * authProvider.setUserDetailsService(userDetailsService());
 * authProvider.setPasswordEncoder(passwordEncoder()); return authProvider; }
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception { auth.authenticationProvider(authenticationProvider()); }
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * http.authorizeRequests().antMatchers("/products/new").hasRole("ADMIN").
 * antMatchers("/products/delete/**")
 * .hasRole("ADMIN").antMatchers("/products/**").authenticated().and().formLogin
 * ().permitAll().and()
 * .logout().permitAll().and().exceptionHandling().accessDeniedPage("/403"); }
 * 
 * }
 */