package project.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@ProcessAPIPrototype
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProcessAPIPrototype {
	// Marker annotation, should be applied to a method within a prototype class
	public interface DataInput {
		List<Integer> readIntegers() throws Exception;
	}
	public interface DataOutput {
		void writeIntegers(List<Integer> data) throws Exception;
	}
	
	public interface StorageComputeAPI{
		void setInput(DataInput input);
		void setOutput(DataOutput output);
		void process() throws Exception;
	}
}

