package com.codmind.swaggerapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codmind.swaggerapi.dto.User;

@RestController
public class UserController {

	private List<User> users = new ArrayList<>();

	public UserController() {
		users.add(new User(1L, "admin"));
		users.add(new User(2L, "supervisor"));
		users.add(new User(3L, "cajero"));
	}

	@GetMapping(value = "users")
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok(users);
	}

	@PutMapping(value = "users")
	public ResponseEntity<User> update(User request) {
		User user = users.stream().filter(currentUser -> currentUser.getId() == request.getId()).findFirst()
				.orElseThrow(() -> new RuntimeException("No existe el usuario"));
		user.setName(request.getName());
		return ResponseEntity.ok(user);
	}

	@PostMapping(value = "users")
	public ResponseEntity<User> create(User request) {
		users.add(request);
		return ResponseEntity.ok(request);
	}

	@DeleteMapping(value = "users/{userId}")
	public ResponseEntity<?> delete(@PathVariable("userId") long userId) {
		User user = users.stream().filter(currentUser -> currentUser.getId() == userId).findFirst()
				.orElseThrow(() -> new RuntimeException("No existe el usuario"));
		users.remove(user);
		return ResponseEntity.ok().build();

	}

	@PostMapping(value = "inverted-sentence")
	public String invertSentence(String sentence) {

		String[] split = sentence.split(" ");
		String newSentence = "";

		for (int i = 0; i < split.length; i++) {
			newSentence = split[i] + " " + newSentence;
		}

		return newSentence;
	}

	@PostMapping(value = "vowel-count")
	public int vowelCount(String sentence) {

		return sentence.replaceAll("(?i)[^aeiou]", "").length();

	}

	@PostMapping(value = "alternate-case")
	public String alternateCase(String sentence) {

		String result = "";
		for (char c : sentence.toCharArray()) {
			if (Character.isUpperCase(c)) {
				result += Character.toLowerCase(c);
			} else {
				result += Character.toUpperCase(c);
			}
		}

		return result;

	}

}
