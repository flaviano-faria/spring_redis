package com.redis.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class UserDTO {

    @NotEmpty(message = "id is required")
	private String id;

    @NotEmpty(message = "name is required")
    private String name;
    
	public UserDTO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
