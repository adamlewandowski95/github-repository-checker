package com.adamlewandowski.githubrepositorychecker.pojo;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "commit",
        "protected"
})

public class BranchPojo {

    @JsonProperty("name")
    private String name;
    @JsonProperty("commit")
    private CommitPojo commitPojo;
    @JsonProperty("protected")
    private Boolean isProtected;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("commit")
    public CommitPojo getCommit() {
        return commitPojo;
    }

    @JsonProperty("commit")
    public void setCommit(CommitPojo commitPojo) {
        this.commitPojo = commitPojo;
    }

    @JsonProperty("protected")
    public Boolean getProtected() {
        return isProtected;
    }

    @JsonProperty("protected")
    public void setProtected(Boolean isProtected) {
        this.isProtected = isProtected;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}