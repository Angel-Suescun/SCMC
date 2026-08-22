package com.scmc.domain.dto.audit;

public record AuditStep (

    Integer stepNumber,

    AuditType title,

    String description,

    String input,

    String output
) {

}
