package model;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Model {

	public Model() {
		super();
	}

	public static String toString(Object object, boolean recursive) {
		if (object == null)
			return "null";

		Class<?> clazz = object.getClass();
		StringBuilder sb = new StringBuilder(clazz.getSimpleName()).append(" {");

		while (clazz != null && !clazz.equals(Object.class)) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field f : fields) {
				if (!Modifier.isStatic(f.getModifiers())) {
					try {
						f.setAccessible(true);
						sb.append(f.getName()).append(" = ").append(f.get(object)).append(",");
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}

			if (!recursive) {
				break;
			}
			clazz = clazz.getSuperclass();
		}

		sb.deleteCharAt(sb.lastIndexOf(","));
		return sb.append("}").toString();
	}
}
