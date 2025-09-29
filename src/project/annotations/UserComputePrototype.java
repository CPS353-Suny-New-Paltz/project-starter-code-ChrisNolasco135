package project.annotations;

public class UserComputePrototype {
	@NetworkAPIPrototype
    public static UserComputeAPI createPrototype() {
        // returning a stub/prototype (not fully implemented yet)
        return new UserComputeAPI() {
            private DataSource source;
            private DataDestination destination;
            private String delimiter = ","; // default delimiter

            @Override
            public void setInputSource(DataSource source) {
                this.source = source;
            }

            @Override
            public void setOutputDestination(DataDestination destination) {
                this.destination = destination;
            }

            @Override
            public void setDelimiters(String delimiter) {
                if (delimiter != null && !delimiter.isEmpty()) {
                    this.delimiter = delimiter;
                }
            }

            @Override
            public void executeJob() {
                System.out.println("Executing job with:");
                System.out.println("Source: " + source.getIdentifier());
                System.out.println("Destination: " + destination.getIdentifier());
                System.out.println("Delimiter: " + delimiter);
            }
        };
    }
}
