package org.norasoft.jenkinsfile.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo2Tests {

	@Test
	public void contextLoads2() throws InterruptedException {
		Thread.sleep(10000);
	}
}

