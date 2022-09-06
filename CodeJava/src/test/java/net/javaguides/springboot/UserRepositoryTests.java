package net.javaguides.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace =Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	 @Autowired
	    private TestEntityManager entityManager;
	     
	    @Autowired
	    private UserRepository repo;
	    
	    @Test
	    public void testCreateUser() {
	        User user = new User();
	        user.setEmail("kavya@gmail.com");
	        user.setPassword("kavya2020");
	        user.setFirstName("Kavya");
	        user.setLastName("D S");
	         
	        User savedUser = repo.save(user);
	         
	        User existUser = entityManager.find(User.class, savedUser.getId());
	        
	        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	         
	    }
	
	   @Test
	   public void testFindUserByEmail()
	   {
		   String email="geetha@gmail.com";
		   
		   User user =repo.findByEmail(email);
		   assertThat(user).isNotNull();
	   }
}
