package com.library;

import com.library.dto.AuthenticationResponse;
import com.library.dto.RegisterRequest;
import com.library.entity.Patron;
import com.library.repository.PatronRepo;
import com.library.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class LibraryApplicationTests {
	@MockBean
	private PatronRepo patronRepo;

	@Autowired
	private AuthenticationService authenticationService;

	@Test
	void testRegister() {



		// Given
		RegisterRequest registerRequest = new RegisterRequest("John", "Doe", "john.doe@example.com", "password", "1234567890");
		Patron existingPatron = new Patron();
		existingPatron.setEmail("john.doe@example.com");
		when(patronRepo.findByEmail("john.doe@example.com")).thenReturn(Optional.of(existingPatron));

		// When
		AuthenticationResponse response = authenticationService.register(registerRequest);

		// Then
		assertTrue(response.getToken().isEmpty());
	}

}
