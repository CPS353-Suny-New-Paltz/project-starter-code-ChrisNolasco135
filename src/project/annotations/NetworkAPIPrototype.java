package project.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NetworkAPIPrototype
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NetworkAPIPrototype {
	public interface NetworkAPI{
		void setInputSource(String sourcePath);
		void setDelimiter(String delimeter);
		void setOutputDestination(String destinationPath);
		void process() throws Exception;
	}
	public interface DataSource{
		String readData() throws Exception;
	}
	public interface DataDestination{
		void writeData(String data) throws Exception;
	}
	
}
