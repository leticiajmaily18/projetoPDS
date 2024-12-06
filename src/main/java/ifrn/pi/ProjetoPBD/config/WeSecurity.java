package ifrn.pi.ProjetoPBD.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WeSecurity {

		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.authorizeHttpRequests( autorize -> autorize
					.requestMatchers("/").permitAll()
					)
			.formLogin(formLogin -> formLogin
					.loginPage("/mercado/login")
			.permitAll())
			.logout(logout -> logout.permitAll());
				
			
			return http.build();
		}
}
