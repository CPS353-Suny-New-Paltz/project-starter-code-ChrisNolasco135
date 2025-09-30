package main.java.api.storage;

import project.annotations.ProcessAPIPrototype;

public class StorageComputePrototype {
	@ProcessAPIPrototype
	public static StorageComputeAPI createPrototype() {
	 return new StorageComputeAPI() {
         @Override
         public DataBatch readData() {
             // For now, just dummy data
             return () -> new int[]{1, 2, 3, 4, 5};
         }

         @Override
         public void writeData(DataBatch data) {
             System.out.println("Writing data:");
             for (int val : data.getData()) {
                 System.out.print(val + " ");
             }
             System.out.println();
         }
     };
 }
}
