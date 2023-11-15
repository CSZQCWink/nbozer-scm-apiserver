import java.util.Date;
import java.util.UUID;

/**
 * @BelongsPackage: PACKAGE_NAME
 * @ClassName: Test
 * @Author: 陈苏洲
 * @Description: 。
 * @CreateTime: 2023-11-08 13:15
 * @Version: 1.0
 */

public class Test {
	@org.junit.Test
	public void test(){
		Date now = new Date();
		System.out.println(now.toString());
		System.out.println(System.currentTimeMillis());
		UUID uuid = UUID.randomUUID();//最常用
		System.out.println(uuid);
	}
}
