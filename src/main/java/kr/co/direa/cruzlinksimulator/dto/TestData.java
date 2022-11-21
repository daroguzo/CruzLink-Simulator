package kr.co.direa.cruzlinksimulator.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data @Builder
public class TestData {
    @NotNull
    private String header;

    @NotNull
    private String data;
}
