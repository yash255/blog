package com.blog.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.blog.config.AppConstants;
import com.blog.blog.models.Role;
import com.blog.blog.repositories.RoleRepo;



@SpringBootApplication
public class BlogApplication implements CommandLineRunner{
    
	@Autowired
	private PasswordEncoder passwordEncoder;
    @Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("professor123"));

		try {
			Role admin = new Role();
			admin.setId(AppConstants.ROLE_ADMIN);
			admin.setName("ADMIN_USER");

			Role normal = new Role();
			normal.setId(AppConstants.ROLE_NORMAL);
			normal.setName("NORMAL_User");

		List<Role> roles  =	List.of(admin,normal);
		List<Role> result = this.roleRepo.saveAll(roles);

		result.forEach(r->{
			System.out.println(r.getName());
		});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
