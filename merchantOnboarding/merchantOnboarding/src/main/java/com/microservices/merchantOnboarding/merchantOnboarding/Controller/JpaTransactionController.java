//package com.microservices.merchantOnboarding.merchantOnboarding.Controller;
//
//import java.net.URI;
//import java.util.List;
//
//import com.ToDoApp.restfulWebServicesJwt.entitymodels.Todo;
//import com.ToDoApp.restfulWebServicesJwt.repositories.JpaTodoRepository;
//import com.microservices.merchantOnboarding.merchantOnboarding.EntityModel.Transaction;
//import com.microservices.merchantOnboarding.merchantOnboarding.Repository.JpaTransactionRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//@CrossOrigin(origins="http://localhost:4200")
//@RestController
//public class JpaTransactionController {
//	
//	
//	@Autowired
//	private JpaTransactionRepository jpaTodoRepository;
//	
//	
//	@GetMapping("/jpa/userProfile/{username}/todos")
//	public List<Transaction> getAllTodos(@PathVariable String username){
//		return jpaTodoRepository.findByUsername(username);
//		
//	}
//	
//	@GetMapping("/jpa/userProfile/{username}/todos/{id}")
//	public Transaction getTodo(@PathVariable String username,@PathVariable long id){
//		return jpaTodoRepository.findById(id).get();
//		
//	}
//	
//	@DeleteMapping("/jpa/userProfile/{username}/todos/{id}")
//	public ResponseEntity<Void> deleteTodo(
//			@PathVariable String username,
//			@PathVariable long id){
//		
//		jpaTodoRepository.deleteById(id);
//		return ResponseEntity.noContent().build();
//	}
//	
//	@PutMapping("/jpa/userProfile/{username}/todos/{id}")
//	public ResponseEntity<Transaction> updateTodo(
//			@PathVariable String username,
//			@PathVariable long id,
//			@RequestBody Todo todo){
//		Todo updatedTodo= jpaTodoRepository.save(todo);
//		return new ResponseEntity<Todo>(updatedTodo,HttpStatus.OK);
//	}
//	
//	@PostMapping("/jpa/userProfile/{username}/todos")
//	public ResponseEntity<Void> createTodo(
//			@PathVariable String username,
//			@RequestBody Transaction todo){
//		Transaction createdTodo= jpaTodoRepository.save(todo);
//		
//		//we need to return the location of the added todo
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//				.path("/{id}").buildAndExpand(createdTodo.getId()).toUri();// here we are taking the current
//		//request path and adding the id to it 
//		return ResponseEntity.created(uri).build();
//	}
//	
//	
//
//}
