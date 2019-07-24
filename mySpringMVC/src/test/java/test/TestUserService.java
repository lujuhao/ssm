package test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.entity.User;
import com.service.UserService;
import com.vo.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})
public class TestUserService {

	@Autowired
	private UserService userService;
	
	@Test
	public void test(){
		Page<User> userPageInfo = userService.selectUserByPage(new Page<User>(),new User());
		for (User user : userPageInfo.getList()) {
			System.out.println(user.getName());
		}
	}
}
