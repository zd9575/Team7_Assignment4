package assign4.src;

import assign4.src.Controllers.MemberController;
import assign4.src.Models.Member;
import assign4.src.Models.Task;
import assign4.src.Repositories.MemberRepository;
import assign4.src.Services.MemberService;
import assign4.src.Services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.springframework.ui.Model;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class SrcApplicationTests {
	@Mock
	private MemberRepository memberRepository;

	@Mock
	private Model model;

	@InjectMocks
	private MemberController memberController;

	@Mock
	private MemberService memberService;

	@Mock
	private TaskService taskService;

	//test case 1
	@Test
	public void testHandleLoginSuccess() {

		Member mockMember = new Member(); //create a member to simulate login
		mockMember.setEmail("james@gmail.com"); //enter email for login
		mockMember.setPassword("password"); //enter password for login
		mockMember.setRole("Employee");
		when(memberRepository.findByEmail("james@gmail.com")).thenReturn(mockMember); //simulate a successful retrieval of a member by email.

		String viewName = memberController.handleLogin(mockMember, model); //call handleLogin() method for testing

		//use this if entered password and username are correct
		assertEquals("redirect:/memberPage", viewName); //check the returned value of handleLogin() method if password is right
		verify(model).addAttribute(eq("currentMember"), any(Member.class)); //check if an attribute named "currentMember" is added to model object
		verifyNoMoreInteractions(model); //Verify that there are no more interactions with the model after the expected interactions.

		//use this if entered password and username are incorrect
		// assertEquals("login", viewName); //check the returned value of handleLogin() method if password is wrong
		// verify(model).addAttribute(eq("passwordError"), eq("Username or password is incorrect. Please try again!"));//check if an attribute named "passwordError" is added to model object
		// verify(model).addAttribute(eq("waitForLogin"), eq(true));//check if an attribute named "waitForLogin" is added to model object
		// verifyNoMoreInteractions(model);//Verify that there are no more interactions with the model after the expected interactions.
	}

	@Test
	public void testTaskCreation() {

		Member mockMember = new Member();
		mockMember.setEmail("lv9591@rit.edu");
		mockMember.setPassword("levi1234");
		mockMember.setRole("Employee");
		mockMember.setFirstName("Levi");
		mockMember.setLastName("Ackerman");

		Task mockTask = new Task(); //create a task to simulate task creation
		mockTask.setName("Laundry"); //enter task name
		mockTask.setDescription("clean the laundry"); //enter description
		mockTask.setAssignedMember(mockMember); //assign employee

		String viewName = memberController.postTaskHandling(mockTask, mockMember, model);

		assertEquals("login",viewName);

	}
}