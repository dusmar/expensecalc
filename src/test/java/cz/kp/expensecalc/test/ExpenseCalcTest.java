package cz.kp.expensecalc.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration({ "classpath:/META-INF/test-applicationContext.xml",
		"classpath:/META-INF/test-applicationContext-persistence.xml" })
public abstract class ExpenseCalcTest extends
		AbstractTransactionalJUnit4SpringContextTests {

}
