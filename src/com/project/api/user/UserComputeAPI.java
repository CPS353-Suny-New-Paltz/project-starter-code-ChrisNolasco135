package com.project.api.user;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserComputeAPI {
	void setInputSource(DataSource source);

    void setOutputDestination(DataDestination destination);

    // If user doesn’t call this, defaults will be applied
    void setDelimiters(String delimiter);

    void executeJob();
}
