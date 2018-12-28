package test.one;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.mockito.internal.util.reflection.Fields;

public class Main {

	public static void main(String[] args) throws Exception {
		Student s1=new Student();
		s1.setAddress("安徽省");
		s1.setAge(15);
		s1.setName("张三");
		s1.setSchool("五里中学");
		Map describe = BeanUtils.describe(s1);
		
		Student s2=new Student();
		s2.setAddress("江苏省");
		s2.setAge(19);
		s2.setName("张三4");
		s2.setSchool("五里大中学");
		Map changeDescribe = BeanUtils.describe(s2);
		System.out.println(describe);
		System.out.println(changeDescribe);
		
		Set keySet = describe.keySet();
		Set cKeySet = changeDescribe.keySet();
		for (Object k : keySet) {
			for(Object ck : cKeySet) {
				if(k.toString().equals(ck.toString())) {
					if(!describe.get(k).toString().equals(changeDescribe.get(ck))) {
	/*					System.out.println(describe.get(k).toString() +"----" + changeDescribe.get(ck));*/
						Field declaredField = Student.class.getDeclaredField(k.toString());
						ColumnName annotation = declaredField.getAnnotation(ColumnName.class);
				/*		System.out.println(annotation.value());*/
						System.out.println("系统检测到您刚刚把"+annotation.value()+"由之前的"+describe.get(k).toString()+"修改为了"+changeDescribe.get(ck));
						
					}
				}
			}
		}
		
	}
}
