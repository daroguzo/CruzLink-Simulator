package kr.co.direa.cruzlinksimulator.dto;

import kr.co.direa.cruzlinksimulator.constant.Encoding;
import kr.co.direa.cruzlinksimulator.constant.MessageType;
import kr.co.direa.cruzlinksimulator.constant.Role;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data @Builder
public class Test {

    @NotNull
    private String name;

    private String host;

    private String port;

    private Encoding encoding;

    private Role role;

    private MessageType messageType;

    @NotNull
    private String sourceChannelId;

    @NotNull
    private String targetChannelId;

    @NotNull
    private String interfaceId;

    private String relativeInterfaceId;

    private boolean isUse;

    @NotNull
    private TestData testData;
}
